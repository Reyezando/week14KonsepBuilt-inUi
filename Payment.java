package week11;
system.out.println("ohaoshdoa");
public abstract class Payment implements ClassInfo {
	protected boolean isPaidOff;
	protected Item item;
	
	public abstract int pay(int bayar);
	
	public Payment() {
		this.isPaidOff = false;
		this.item = null;
	}
	
	public Payment (Item item) {
		this.isPaidOff = false;
		this.item = item;
	}
	
	public Item getItem() {
		return item;
	}
	
	public String getItemName() {
		return item.getName();
	}
	
	public String getStatus() {
		if(isPaidOff) {
			return "FINISHED";
		}
		return "ON PROGRESS";
	}
	
	public int getRemainingAmount() {
		if(isPaidOff) {
			return 0;
		}
		return item.getPrice();
	}
	
	public boolean getPaidOff() {
		return isPaidOff;
	}
}
