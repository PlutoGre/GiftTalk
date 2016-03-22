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
}
