package kr.spring.board.vo;

public class BoardReFavVO {
	private long re_num;
	private long mem_num;
	
	public long getRe_num() {
		return re_num;
	}
	public void setRe_num(long re_num) {
		this.re_num = re_num;
	}
	public long getMem_num() {
		return mem_num;
	}
	public void setMem_num(long mem_num) {
		this.mem_num = mem_num;
	}
	
	@Override
	public String toString() {
		return "BoardReFavVO [re_num=" + re_num + ", mem_num=" + mem_num + "]";
	}
	
}
