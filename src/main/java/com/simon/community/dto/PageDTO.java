package com.simon.community.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class PageDTO {
    private List<QuestionDTO> questionDTOS;
    private boolean showPrevious;
    private boolean showFirstPage;
    private boolean showNext;
    private boolean showEndPage;
    private Integer currentPage;
    private List<Integer> pages = new ArrayList<>();
    private Integer totalPage;

    /**
     * 设置分页相关参数
     * @param pageCount 总数量
     * @param page 当前页
     * @param size 页面承载数据量
     */
    public void setPagination(Integer pageCount, Integer page, Integer size) {
        // 计算总页数
        if (pageCount % size == 0) {
            totalPage = pageCount / size;
        } else {
            totalPage = pageCount / size + 1;
        }
        // 参数验证：
        if (page < 1) {
            page = 1;
        }
        if (page > totalPage) {
            page = totalPage;
        }
        // 设置当前页，页面传参使用
        this.currentPage = page;
        // 分页展示的页码
        // 如果当前页是1： 1、2、3、4、5、6、7
        // 如果当前页是2： 1、2、3、4、5、6、7
        // 如果当前页是3： 1、2、3、4、5、6、7
        // 如果当前页是4： 1、2、3、4、5、6、7
        // 如果当前页是5： 2、3、4、5、6、7、8
        // 如果当前页是6： 3、4、5、6、7、8、9
        // 。。。
        pages.add(page);
        // 当前页之前和之后都设置3个页数
        for (int i = 1; i <= 3; i++) {
            System.out.println(i);
            System.out.println(pages);
            if (page - i > 0) {
                // 头插
                pages.add(0, page - i);
            }
            if (page + i <= totalPage) {
                // 尾插
                pages.add(page + i);
            }
        }
        System.out.println(pages);
        // 只有当页码为1时，才不显示"上一页"的按钮
        showPrevious = page != 1;
        System.out.println("showPrevious: " + String.valueOf(showPrevious));
        // 当前页为最后一页时，不显示"下一页"的按钮
        showNext = !totalPage.equals(page);
        System.out.println("showNext: " + String.valueOf(showNext));
        // 是否展示"第一页"
        showFirstPage = !pages.contains(1);
        System.out.println("showFirstPage: " + String.valueOf(showFirstPage));
        // 是否展示"最后一页"
        showEndPage = !pages.contains(totalPage);
        System.out.println("showEndPage: " + String.valueOf(showEndPage));
    }
}
