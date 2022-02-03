package com.lq.bean;

public class LeiXinTj 
{
	private String name;   //
	private String num;  //
	public LeiXinTj(String name,String num) {
		this.name=name;
		this.num=num;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getNum() {
		return num;
	}
	public void setNum(String num) {
		this.num = num;
	}
}


//select typet,count(1) As counts from xinjian GROUP BY typet;  查询不同类型信件个数
//select answering,count(1) As counts from xinjian GROUP BY answering;  查询不同部门回复个数
//SELECT COUNT(1) AS num, YEAR(send_time) as year FROM xinjian GROUP BY YEAR(send_time); 查询每年统计数据

