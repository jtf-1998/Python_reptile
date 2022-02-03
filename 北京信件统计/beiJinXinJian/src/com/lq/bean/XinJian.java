package com.lq.bean;

public class XinJian {
    private String Id;    //信件编号1
    private String type;  //信件类型2
    private String massage;  //信件简单内容3
    private String statue;  //信件状态4
    private String send_time; //发起时间5
    private String addresser;  //发信人6
    private String asker;   //网友同问7
    private String details;   //信件详细内容8
    private String answering;  //回信人9
    private String answer_time;   //回信时间10
    private String reply;    //回复内容11

    public XinJian() {
    }

    public XinJian(String Id,String type,String massage,String statue,String send_time,String addresser,
                   String asker,String details,String answering,String answer_time,String reply) {
        this.Id=Id;
        this.type = type;
        this.massage=massage;
        this.statue=statue;
        this.send_time=send_time;
        this.addresser=addresser;
        this.asker=asker;
        this.details=details;
        this.answering=answering;
        this.answer_time=answer_time;
        this.reply=reply;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getType() {

        return type;
    }

    public String getMassage() {
        return massage;
    }

    public void setMassage(String massage) {
        this.massage = massage;
    }

    public String getStatue() {
        return statue;
    }

    public void setStatue(String statue) {
        this.statue = statue;
    }

    public String getSend_time() {
        return send_time;
    }

    public void setSend_time(String send_time) {
        this.send_time = send_time;
    }

    public String getAddresser() {
        return addresser;
    }

    public void setAddresser(String addresser) {
        this.addresser = addresser;
    }

    public String getAsker() {
        return asker;
    }

    public void setAsker(String asker) {
        this.asker = asker;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getAnswering() {
        return answering;
    }

    public void setAnswering(String answering) {
        this.answering = answering;
    }

    public String getAnswer_time() {
        return answer_time;
    }

    public void setAnswer_time(String answer_time) {
        this.answer_time = answer_time;
    }

    public String getReply() {
        return reply;
    }

    public void setReply(String reply) {
        this.reply = reply;
    }

    public String toString()
    {
        return this.getId()+"\t"+this.getSend_time()+"\t"+this.getType()+"\t"+this.getMassage()+"\t"+
                this.getStatue()+"\t"+this.getAddresser()+"\t"+this.getAsker()+"\t"+this.getDetails()+"\t"+
                this.getAnswering()+"\t"+this.getAnswer_time()+"\t"+this.getReply();
    }


}
