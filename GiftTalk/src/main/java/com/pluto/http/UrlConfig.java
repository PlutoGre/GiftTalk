package com.pluto.http;

/**
 * Created by Pluto on 2016/3/16.
 */
public interface UrlConfig {

    /**
     * 精选界面的ViewPager的URL
     */
    public static final String HAND_PICK_VIEW_PAGER_URL = "http://api.liwushuo.com/v2/banners";

    /**
     * 精选界面的ViewPager点击的详情URL
     */
    public static final String HAND_PICK_VIEW_PAGER_URL1 = "http://api.liwushuo.com/v2/collections/";
    public static final String HAND_PICK_VIEW_PAGER_URL2 = "/posts?limit=20&offset=0";

    /**
     * 精选界面的横向ListView
     */
    public static final String HAND_PICK_HORIZONTAL_LIST_VIEW_URL = "http://api.liwushuo.com/v2/secondary_banners?gender=1&generation=1";

    /**
     * 精选界面ListView的URL
     */
    public static final String HAND_PICK_LIST_VIEW = "http://api.liwushuo.com/v2/channels/101/items?ad=2&gender=1&generation=2&limit=20&offset=0";

    /**
     * 指导界面中其它界面的URL
     */
    public static final String HOME_OTHERS_URL_1 = "http://api.liwushuo.com/v2/channels/";
    public static final String HOME_OTHER_URL_2 = "/items?gender=1&limit=20&offset=0&generation=2";

    /**
     * 热门界面中GridView的URL
     */
    public static final String SELECT_GRID_VIEW_URL = "http://api.liwushuo.com/v2/items?gender=1&limit=20&generation=2&offset=0";

    /**
     * 分类界面中的攻略界面品类及以下的URL
     */
    public static final String CATEGORY_STRATEGY_URL = "http://api.liwushuo.com/v2/channel_groups/all";

    /**
     * 分类界面中的攻略界面的头布局的URL
     */
    public static final String CATEGORY_STRATEGY_HEAD_URL = "http://api.liwushuo.com/v2/collections?limit=10&offset=0";

    /**
     * 分类界面中的礼物界面的URL
     */
    public static final String CATEGORY_GIFT_URL = "http://api.liwushuo.com/v2/item_categories/tree";

    /**
     * 热门界面中GridView点进去的详情界面的URL，需要加上对应的id
     */
    public static final String SELECT_DETAIL_URL = "http://api.liwushuo.com/v2/items/";

    /**
     * 热门界面中GridView点进去的详情界面的的评论的URL
     */
    public static final String SELECT_DETAIL_COMMENT_URL_1 = "http://api.liwushuo.com/v2/items/";
    public static final String SELECT_DETAIL_COMMENT_URL_2 = "/comments?limit=20&offset=0";

    /**
     * 分类界面中的攻略界面品类及以下点进去的详情的URL
     */
    public static final String CATEGORY_STRATEGY_DETAIL_URL_1 = "http://api.liwushuo.com/v2/channels/";
    public static final String CATEGORY_STRATEGY_DETAIL_URL_2 = "/items?limit=20&gender=1&offset=0&generation=2&order_by=now";

    /**
     * 分类界面中的礼物界面详情点进去的详情的URL
     */
    public static final String CATEGORY_GIFT_DETAIL_URL_1 = "http://api.liwushuo.com/v2/item_subcategories/";
    public static final String CATEGORY_GIFT_DETAIL_URL_2 = "/items?limit=20&offset=0";

    /**
     * 选礼神器的默认界面URL
     */
    public static final String SELECT_GIFT_GOD_URL = "http://api.liwushuo.com/v2/search/item_by_type?limit=20&offset=0";

    /**
     * 搜索的结果中礼物界面的URL
     */
    public static final String SEARCH_GIFT_URL = "http://api.liwushuo.com/v2/search/item?limit=20&offset=0&sort=&keyword=";

    /**
     * 搜索的结果中攻略界面的URL
     */
    public static final String SEARCH_STRATEGY_URL = "http://api.liwushuo.com/v2/search/post?limit=20&offset=0&sort=&keyword=";

    /**
     * 筛选界面的popup_window的URL
     */
    public static final String POPUP_WINDOW_URL = "http://api.liwushuo.com/v2/search/item_filter";

    /**
     * 搜索界面的流式布局的URL
     */
    public static final String SEARCH_FLOW_LAYOUT_URL = "http://api.liwushuo.com/v2/search/hot_words";
}
