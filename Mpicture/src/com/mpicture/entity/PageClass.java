package com.mpicture.entity;

import org.springframework.stereotype.Component;

/**
 * 分页类
 * @author wangxin
 * @version 2017-04-17
 */
@Component
public class PageClass {
		//当前页起始下标
		private Integer nowpage;
		//查询结果数量
		private Integer count;
		//总共页面数量
		private Integer pagetotal;
		//每页数量
		private Integer pagecount=6;
		//分页查询起始位置
		private Integer limitstart;
		
		
		public Integer getLimitstart() {
			return limitstart;
		}
		
		
		public PageClass() {
			super();
		}
		
		public PageClass(Integer nowpage, Integer count, Integer pagetotal, Integer pagecount, Integer limitstart) {
			super();
			this.nowpage = nowpage;
			this.count = count;
			this.pagetotal = pagetotal;
			this.pagecount = pagecount;
			this.limitstart = limitstart;
		}
		@Override
		public String toString() {
			return "PageClass [nowpage=" + nowpage + ", count=" + count + ", pagetotal=" + pagetotal + ", pagecount="
					+ pagecount + ", limitstart=" + limitstart + "]";
		}
		public Integer getNowpage() {
			return nowpage;
		}
		public void setNowpage(Integer nowpage) {
			this.nowpage = nowpage;
			this.limitstart=(nowpage-1)*this.pagecount;
		}
		public Integer getCount() {
			return count;
		}
		public void setCount(Integer count) {
			this.count = count;
			if(count%pagecount!=0){
				this.pagetotal=count/pagecount+1;
			}else{
				this.pagetotal = count/pagecount;
			}
		}
		
		public Integer getPagetotal() {
			return pagetotal;
		}
		
		public Integer getPagecount() {
			return pagecount;
		}
		public void setPagecount(Integer pagecount) {
			this.pagecount = pagecount;
		}


}
