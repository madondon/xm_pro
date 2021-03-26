package com.offcn.util;
/**
 * ��ҳ����
 * @author IBM
 */
public class PageTools {
	//ÿҳ�������ر�������
	private int size;
	//���������ر�������
	private int countRows;
	//��ǰҳ���ر�������
	private int current_page;
	//��ҳ��
	private int count_page;
	//����
	private int index;
	//��һҳ
	private int pre_page;
	//��һҳ
	private int next_page;
	
	public PageTools(int size, int countRows, String current_page) {
		this.size = size;
		this.countRows = countRows;
		//��ʼ����ǰҳ��Ϣ
		init_current_page(current_page);
		//��ʼ����ҳ��
		init_count_page();
		//��ʼ������
		init_index();
		//��һҳ
		init_pre_page();
		//��һҳ
		init_next_page();
	}
	
	//��һҳ
	private void init_next_page() {
		if(this.current_page == this.count_page) {
			this.next_page = this.count_page;
		}else {
			this.next_page = this.current_page +1;
		}
	}

	//��һҳ
	private void init_pre_page() {
		if(this.current_page == 1) {
			this.pre_page = 1;
		}else {
			this.pre_page = this.current_page - 1;
		}
	}

	//��ʼ������
	private void init_index() {
		//(��ǰҳ - 1) * ÿҳ���� ;
		this.index = (this.current_page-1) * this.size;
	}

	//��ʼ����ҳ��
	private void init_count_page() {
		//��ʽ  (������/ÿҳ������) + (������%ÿҳ������)>0?1:0
		this.count_page = (this.countRows/this.size) + (this.countRows%this.size>0?1:0);
	}

	//��ʼ����ǰҳ��Ϣ
	private void init_current_page(String current_page) {
		if(null == current_page || "".equals(current_page)) {
			this.current_page = 1;
		}else {
			this.current_page = Integer.parseInt(current_page);
		}
	}

	//��Ϊҳ���dao�㶼��Ҫ ���ߵ����ԣ�����ֱ���ṩgetter��������
	public int getSize() {
		return size;
	}

	public int getCountRows() {
		return countRows;
	}

	public int getCurrent_page() {
		return current_page;
	}

	public int getCount_page() {
		return count_page;
	}

	public int getIndex() {
		return index;
	}

	public int getPre_page() {
		return pre_page;
	}

	public int getNext_page() {
		return next_page;
	}
	
	
	
}
