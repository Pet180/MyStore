package cn.tedu.store.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.tedu.store.bean.Address;
import cn.tedu.store.bean.ResponseResult;
import cn.tedu.store.service.AddressService;

@Controller
@RequestMapping("/address")
public class AddressController extends BaseController{
	@Resource
	private AddressService addressService;
	//显示addressAdmin页面
	@RequestMapping("/showAddress.do")
	public String showAddress(){
		System.out.println("AddressController.showAddress()");
		return "addressAdmin";
	}
	/**
	 * url:/address/addAddress.do
	 * 请求参数:9个框，session(uid)共十个
	 * 请求方式:post
	 * 响应方式：ResponseBody
	 *
	 */
	@RequestMapping("/addAddress.do")
	@ResponseBody
	public ResponseResult<Void> addAddress(HttpSession session,
			@RequestParam("receiverName") String recvName,
			@RequestParam("receiverState") String recvProvince,
			@RequestParam("receiverCity") String recvCity,
			@RequestParam("receiverDistrict") String recvArea,
			@RequestParam("receiverAddress") String recvAddress,
			@RequestParam("receiverMobile") String recvPhone,
			@RequestParam("receiverPhone") String recvTel,
			@RequestParam("receiverZip") String recvZip,
			@RequestParam("addressName") String recvTag){
		System.out.println("AddressController.addAddress()");
		ResponseResult<Void> rr = new ResponseResult<Void>();
		Address address = new Address();
		address.setUid(this.getId(session));
		address.setRecvName(recvName);
		address.setRecvProvince(recvProvince);
		address.setRecvCity(recvCity);
		address.setRecvArea(recvArea);
		address.setRecvAddress(recvAddress);
		address.setRecvPhone(recvPhone);
		addressService.addAddress(address);
		rr.setState(1);
		rr.setMessage("添加成功");
		return rr;
	}
	/**
	 * url:/address/getAddressByUid.do
	 * 请求参数：session(uid)
	 * 请求方式:get
	 * 响应方式:ResponseBody
	 * 
	 */
	@RequestMapping("/getAddressByUid.do")
	@ResponseBody
	public ResponseResult<List<Address>> getAddressByUid(HttpSession session){
		System.out.println("AddressController.getAddressByUid()");
		ResponseResult<List<Address>> rr = new ResponseResult<List<Address>>();
		rr.setData(addressService.getAddressByUid(this.getId(session)));
		return rr;
	}
	/**
	 *url:/address/setDefault.do
	 *请求参数:session(uid),id
	 *请求方式:get
	 *响应方式：ResponseBody
	 */
	/**
	 * 设置默认收获地址
	 * @param session
	 * @param id
	 * @return
	 */
	@RequestMapping("/setDefault.do")
	@ResponseBody
	public ResponseResult<Void> setDefault(HttpSession session,Integer id){
		System.out.println("AddressController.setDefault()");
		ResponseResult<Void> rr = new ResponseResult<Void>();
		try {
			addressService.setDefault(this.getId(session), id);
			//成功状态码:1
			rr.setState(1);
			rr.setMessage("修改成功");
		} catch (Exception e) {
			//失败状态码:0
			rr.setState(0);
			rr.setMessage(e.getMessage());
		}
		return rr;
	}
	/**
	 * url:/address/getAddressById.do
	 * 请求参数:id
	 * 请求方式:get
	 * 响应方式:ResponseBody
	 */
	@RequestMapping("/getAddressById.do")
	@ResponseBody
	public ResponseResult<Address> getAddressById(Integer id){
		System.out.println("AddressController.getAddressById()");
		ResponseResult<Address> rr = new ResponseResult<Address>();
		rr.setData(addressService.getAddressById(id));
		return rr;
	}

	/**
	 * url:/address/updateAddress.do
	 * 请求参数：form数据
	 * 请求方式:post
	 * 响应方式:ResponseBody
	 * 
	 */
	@RequestMapping("/updateAddress.do")
	@ResponseBody
	public ResponseResult<Void> updateAddress(
			Integer id,
			@RequestParam("receiverName") String recvName,
			@RequestParam("receiverState") String recvProvince,
			@RequestParam("receiverCity") String recvCity,
			@RequestParam("receiverDistrict") String recvArea,
			@RequestParam("receiverAddress") String recvAddress,
			@RequestParam("receiverMobile") String recvPhone,
			@RequestParam("receiverPhone") String recvTel,
			@RequestParam("receiverZip") String recvZip,
			@RequestParam("addressName") String recvTag){
		System.out.println("AddressController.updateAddress()");
		ResponseResult<Void> rr = new ResponseResult<Void>();
		Address address = new Address();
		address.setId(id);
		address.setRecvName(recvName);
		address.setRecvProvince(recvProvince);
		address.setRecvCity(recvCity);
		address.setRecvArea(recvArea);
		address.setRecvAddress(recvAddress);
		address.setRecvPhone(recvPhone);
		address.setRecvTel(recvTel);
		address.setRecvZip(recvZip);
		address.setRecvTag(recvTag);
		addressService.updateAddressById(address);
		rr.setState(1);
		rr.setMessage("修改成功");
		return rr;
	}
	@RequestMapping("/deleteAddress.do")
	@ResponseBody
	public ResponseResult<Void> deleteAddress(Integer id){
		System.out.println("AddressController.deleteAddress()");
		ResponseResult<Void> rr = new ResponseResult<Void>();
		addressService.deleteAddress(id);
		rr.setState(1);
		rr.setMessage("删除成功");
		return rr;
	}
	
}	

