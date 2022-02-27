package com.hrm.entity;

public class Page {
    // 页码：客户端传入后台
    private int pageNum;
    // 每页显示的行数：自定义
    private int pageRow;
    // 总行数：SQL统计的
    private int totalRow;
    // 总页数：计算获得
    // totalRow / pageRow
    // 通过取余判断是否能除尽：totalRow % pageRow
    // totalPage = totalRow % pageRow == 0 ? totalRow / pageRow : (totalRow / pageRow) + 1;
    private int totalPage;
    // 起始行的下标   select .....  LIMIT startIndex,pageRow;
    private int startIndex;

    public Page(int pageNum, int pageRow, int totalRow) {
//        this.pageNum = pageNum;
        this.pageRow = pageRow;
        this.totalRow = totalRow;
        this.totalPage  = totalRow % pageRow == 0 ? totalRow / pageRow : (totalRow / pageRow) + 1;

        // 如果页码大于总行数，则跳转到最后一页
        this.pageNum = pageNum > this.totalPage ? this.totalPage : pageNum;

        // 起始行的下标 = （页码 - 1） * 每页显示的行数
        this.startIndex = (this.pageNum - 1) * pageRow;
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getPageRow() {
        return pageRow;
    }

    public void setPageRow(int pageRow) {
        this.pageRow = pageRow;
    }

    public int getTotalRow() {
        return totalRow;
    }

    public void setTotalRow(int totalRow) {
        this.totalRow = totalRow;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getStartIndex() {
        return startIndex;
    }

    public void setStartIndex(int startIndex) {
        this.startIndex = startIndex;
    }
}
