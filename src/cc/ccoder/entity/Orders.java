package cc.ccoder.entity;

/**
 * 
 * @author chencong 消费实体
 */
public class Orders {

	private Integer id;
	private String ClotherType;
	private double price;
	private double vipPrice;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getClotherType() {
		return ClotherType;
	}

	public void setClotherType(String clotherType) {
		ClotherType = clotherType;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public double getVipPrice() {
		return vipPrice;
	}

	public void setVipPrice(double vipPrice) {
		this.vipPrice = vipPrice;
	}

	public Orders(Integer id, String clotherType, double price, double vipPrice) {
		super();
		this.id = id;
		ClotherType = clotherType;
		this.price = price;
		this.vipPrice = vipPrice;
	}

	public Orders() {
		super();
	}

	@Override
	public String toString() {
		return "Orders [id=" + id + ", ClotherType=" + ClotherType + ", price=" + price + ", vipPrice=" + vipPrice
				+ "]";
	}

}
