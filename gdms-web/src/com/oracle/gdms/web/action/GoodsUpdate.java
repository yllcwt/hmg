package com.oracle.gdms.web.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;
import com.oracle.gdms.entity.GoodsEntity;
import com.oracle.gdms.service.GoodsService;
import com.oracle.gdms.util.Factory;

@WebServlet("/admin/goods/update.php")
public class GoodsUpdate extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String name = request.getParameter("name");
		String spec = request.getParameter("spec");
		String amout = request.getParameter("amout");
		String goodsid = request.getParameter("goodsid");
		String price = request.getParameter("price");
		System.out.println(name+spec+amout+goodsid+price);
		
		GoodsEntity gm = new GoodsEntity();
		
		int i= price.indexOf("￥");
		if(i!=-1) price = price.substring(i+1);
		System.out.println(price);
		gm.setName(name);
		gm.setAmount(Float.parseFloat(amout));
		gm.setPrice(Float.parseFloat(price));
		gm.setGoodsid(Integer.parseInt(goodsid));
		gm.setSpec(spec);
		
		GoodsService goodsservice = (GoodsService) Factory.getInstance().getObject("goods.service.impl");
		int count = goodsservice.update(gm);
		response.setContentType("application/json:charset=utf-8");
		JSONObject j = new JSONObject();
		
		if(count>0) {
			j.put("code", 0);
			j.put("msg", "更新成功");
		}else {
			j.put("code", 1005);
			j.put("msg", "跟新商品失败");
		}
		response.getWriter().print(j.toJSONString());
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
