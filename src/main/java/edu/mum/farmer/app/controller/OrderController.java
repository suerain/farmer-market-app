package edu.mum.farmer.app.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import edu.mum.farmer.app.domain.Member;
import edu.mum.farmer.app.domain.Order;
import edu.mum.farmer.app.domain.OrderLine;
import edu.mum.farmer.app.domain.Product;
import edu.mum.farmer.app.service.IOrderService;
import edu.mum.farmer.app.service.ProductService;
import edu.mum.farmer.app.service.MemberService;

@Controller
@SessionAttributes("currentorder")
public class OrderController {

	@Autowired
	private IOrderService orderService;

	@Autowired
	private ProductService productService;

	@Autowired
	private MemberService memberService;

	@RequestMapping(value = "/addToCart/{id}", method = RequestMethod.GET)
	public @ResponseBody Collection<OrderLine> addToCart(@PathVariable("id") long id, Principal user,
			HttpSession session) {
		Member member = memberService.findByUserName(user.getName()).get(0);
		Order currentorder = (Order) session.getAttribute("currentorder");
		if (currentorder == null) {
			currentorder = member.getOrder();
			session.setAttribute("currentorder", currentorder);
			System.out.println("current order is empty");
		}
		Product p = productService.findOne(id);
		currentorder.addProduct(p);
		return currentorder.getOrderLineList();
	}

	@RequestMapping(value = "/removeFromCart/{id}", method = RequestMethod.GET)
	public @ResponseBody Collection<OrderLine> removeFromCart(@PathVariable("id") long id, Principal user,
			HttpSession session) {
		Member member = memberService.findByUserName(user.getName()).get(0);
		Order currentorder = (Order) session.getAttribute("currentorder");
		if (currentorder == null) {
			currentorder = member.getOrder();
			session.setAttribute("currentorder", currentorder);
		}
		Product p = productService.findOne(id);
		currentorder.removeProduct(p);
		return currentorder.getOrderLineList();
	}

	@RequestMapping(value = "/checkout", method = RequestMethod.GET)
	public String goToDashBoard(Model model, Principal user, HttpSession session) {
		Member member = memberService.findByUserName(user.getName()).get(0);
		Order currentorder = (Order) session.getAttribute("currentorder");
		if (currentorder == null) {
			currentorder = member.getOrder();
			session.setAttribute("currentorder", currentorder);
		}
		return "checkOut";
	}

	@RequestMapping(value = "/checkout", method = RequestMethod.POST)
	public String checkout(@Valid Order order, BindingResult bindingResult, Model model, HttpSession session,
			SessionStatus status, Principal user) {
		if (bindingResult.hasErrors()) {
			System.out.println("Has error in validation");
			return "checkOut";
		}
		Member member = memberService.findByUserName(user.getName()).get(0);
		member.getOrder().setOrderLineList(new ArrayList<OrderLine>());
		orderService.saveOrder(member.getOrder());
		model.addAttribute("products", productService.findAllProducts());
		session.removeAttribute("currentorder");
		status.setComplete();
		return "customerdashboard";
	}

	@RequestMapping("/logout")
	public String logout(Model model, HttpServletRequest servletRequest, Principal user, HttpSession session,
			SessionStatus status) throws ServletException {
		Member member = memberService.findByUserName(user.getName()).get(0);
		Order currentorder = (Order) session.getAttribute("currentorder");
		if (currentorder == null) {
			currentorder = member.getOrder();
			session.setAttribute("currentorder", currentorder);
		}
		orderService.saveOrder(currentorder);
		session.removeAttribute("currentorder");
		status.setComplete();
		servletRequest.logout();
		return "redirect:";
	}

}
