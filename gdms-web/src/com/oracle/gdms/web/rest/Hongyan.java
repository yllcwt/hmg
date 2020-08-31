package com.oracle.gdms.web.rest;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.alibaba.fastjson.JSONObject;
import com.oracle.gdms.entity.GoodsModel;
import com.oracle.gdms.entity.ResponseEntity;
import com.oracle.gdms.service.GoodsService;
import com.oracle.gdms.util.Factory;

@Path("/hongyan")
public class Hongyan {
	
	@Path("/sing")
	@GET
	public String sing() {
		System.out.println("唱歌");
		return "";
	}
	
	@Path("sing/{name}")
	@GET
	public String sing(@PathParam("name") String name) {
		System.out.println(name);
		return  "OK";
	}
	
	@Path("sing/ci")
	@GET
	public String singOne(@QueryParam("name") String name) {
		System.out.println(name);
		return  "CI";
	}
	
	@Path("/push/one")
	@POST
	public String push(@FormParam("name") String name) {
		System.out.println("商品名称=" + name);
		return "form";
	}
	@Path("/push/json")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public String pushJson(String jsonparam) {
		System.out.println(jsonparam);
		JSONObject j = JSONObject.parseObject(jsonparam);
		String goodsid = j.getString("goodsid"); 
		String gtid = j.getString("gtid");
		
		System.out.println(goodsid + gtid);
		GoodsService gs = (GoodsService) Factory.getInstance().getObject("update.goods.type");
		int count = gs.updateGoodsType(goodsid, gtid);
		
		
		if(count > 0) {
			System.out.println("更新成功");
		}else {
			System.out.println("更新失败");
		}
		
		return "form";
	}
	@Path("/goods/json")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public String GoodsJSON(String jsonparam) {
		
		JSONObject j = JSONObject.parseObject(jsonparam);
		
		String gtid = j.getString("gtid");
		
		System.out.println("++++"+ gtid);
		GoodsService gs = (GoodsService) Factory.getInstance().getObject("select.goods");
		
		List<GoodsModel> list = gs.SearchGoods(gtid);
		j.put("list", list);
		for( GoodsModel a : list) {
			System.out.println(""+a.getName());
		}
		
		return j.toJSONString();
	}
	

@Path("/goods/push/one")
@POST
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)	
    public ResponseEntity pushGoodsOne(String jsonparam) {

	ResponseEntity r= new ResponseEntity();
	
	try {
			JSONObject j=JSONObject.parseObject(jsonparam);
	String gs =j.getString("goods");
	GoodsModel goods=JSONObject.parseObject(gs,GoodsModel.class);
	System.out.println("服务端收到了");
	System.out.println("商品id="+goods.getGoodsid());
	System.out.println("商品名称="+goods.getName());
	
		r.setCode(0);
		r.setMessage("推送成功，aaa");
		
		
	} catch (Exception e) {
		// TODO 自动生成的 catch 块
		e.printStackTrace();
		r.setCode(1117);
		r.setMessage("推送失败"+jsonparam);
		
		
	}
	
	return r;
    }

}
