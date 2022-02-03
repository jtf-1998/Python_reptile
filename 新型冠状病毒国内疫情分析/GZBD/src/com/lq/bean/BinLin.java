package com.lq.bean;

public class BinLin {
	private int id;   //自增长
	private String nd;  //时间
	private int confirm;  //全国确诊
	private int dead;    //全国死亡
	private int suspect;   //全国疑似
	private int heal;   //全国治愈
	private String shen;   //省
	private String diqu;   //地区
	private int dconfirm;  //地区确诊
	private int dheal;    //地区治愈
	private int ddead;    //地区死亡
	
	public BinLin(int id, String nd, int confirm, int dead, int suspect, int heal, String shen, String diqu,
			int dconfirm, int dheal, int ddead) {
		super();
		this.id = id;
		this.nd = nd;
		this.confirm = confirm;
		this.dead = dead;
		this.suspect = suspect;
		this.heal = heal;
		this.shen = shen;
		this.diqu = diqu;
		this.dconfirm = dconfirm;
		this.dheal = dheal;
		this.ddead = ddead;
	}
	
	public BinLin(String nd, int confirm, int dead, int suspect, int heal, String shen, String diqu, int dconfirm,
			int dheal, int ddead) {
		super();
		this.nd = nd;
		this.confirm = confirm;
		this.dead = dead;
		this.suspect = suspect;
		this.heal = heal;
		this.shen = shen;
		this.diqu = diqu;
		this.dconfirm = dconfirm;
		this.dheal = dheal;
		this.ddead = ddead;
	}
	
	public BinLin(String nd, int confirm, int dead, int suspect, int heal, String shen, int dconfirm, int dheal,
			int ddead) {
		super();
		this.nd = nd;
		this.confirm = confirm;
		this.dead = dead;
		this.suspect = suspect;
		this.heal = heal;
		this.shen = shen;
		this.dconfirm = dconfirm;
		this.dheal = dheal;
		this.ddead = ddead;
	}

	public BinLin(String nd, int confirm, int dead, int suspect, int heal) {
		super();
		this.nd = nd;
		this.confirm = confirm;
		this.dead = dead;
		this.suspect = suspect;
		this.heal = heal;
	}
	
	public BinLin(String shen, String diqu, int dconfirm, int dheal, int ddead) {
		super();
		this.shen = shen;
		this.diqu = diqu;
		this.dconfirm = dconfirm;
		this.dheal = dheal;
		this.ddead = ddead;
	}

	public BinLin() {
		super();
		// TODO Auto-generated constructor stub
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNd() {
		return nd;
	}
	public void setNd(String nd) {
		this.nd = nd;
	}
	public int getConfirm() {
		return confirm;
	}
	public void setConfirm(int confirm) {
		this.confirm = confirm;
	}
	public int getDead() {
		return dead;
	}
	public void setDead(int dead) {
		this.dead = dead;
	}
	public int getSuspect() {
		return suspect;
	}
	public void setSuspect(int suspect) {
		this.suspect = suspect;
	}
	public int getHeal() {
		return heal;
	}
	public void setHeal(int heal) {
		this.heal = heal;
	}
	public String getShen() {
		return shen;
	}
	public void setShen(String shen) {
		this.shen = shen;
	}
	public String getDiqu() {
		return diqu;
	}
	public void setDiqu(String diqu) {
		this.diqu = diqu;
	}
	public int getDconfirm() {
		return dconfirm;
	}
	public void setDconfirm(int dconfirm) {
		this.dconfirm = dconfirm;
	}
	public int getDheal() {
		return dheal;
	}
	public void setDheal(int dheal) {
		this.dheal = dheal;
	}
	public int getDdead() {
		return ddead;
	}
	public void setDdead(int ddead) {
		this.ddead = ddead;
	}
	@Override
	public String toString() {
		return "BinLin [nd=" + nd + ", confirm=" + confirm + ", dead=" + dead + ", suspect=" + suspect + ", heal="
				+ heal + ", shen=" + shen + ", diqu=" + diqu + ", dconfirm=" + dconfirm + ", dheal=" + dheal
				+ ", ddead=" + ddead + "]";
	}
	

}
