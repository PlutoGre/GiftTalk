package com.pluto.bean;

import java.util.List;

/**
 * Created by Pluto on 2016/3/17.
 */
public class HandPickRecyclerViewInfo {


    /**
     * code : 200
     * data : {"secondary_banners":[{"ad_monitors":[],"id":27,"image_url":"http://img01.liwushuo.com/image/160317/bpr08shay.jpg-w720","target_url":"liwushuo:///page?type=post&post_id=1038439&page_action=navigation","webp_url":"http://img01.liwushuo.com/image/160317/bpr08shay.jpg?imageView2/0/w/720/q/85/format/webp"},{"ad_monitors":[],"id":1,"image_url":"http://img03.liwushuo.com/image/160113/45r676dkq.jpg-w720","target_url":"liwushuo:///page?type=url&url=https%3A%2F%2Fevent.liwushuo.com%2Ftopics%2Fdaily-lucky","webp_url":"http://img03.liwushuo.com/image/160113/45r676dkq.jpg?imageView2/0/w/720/q/85/format/webp"},{"ad_monitors":[],"id":7,"image_url":"http://img02.liwushuo.com/image/160317/qwsasuioc.jpg-w720","target_url":"liwushuo:///page?type=post&post_id=1038619&page_action=navigation","webp_url":"http://img02.liwushuo.com/image/160317/qwsasuioc.jpg?imageView2/0/w/720/q/85/format/webp"},{"ad_monitors":[],"id":17,"image_url":"http://img03.liwushuo.com/image/151229/rwdkqqrpk.jpg-w720","target_url":"liwushuo:///page?type=topic&topic_id=187","webp_url":"http://img03.liwushuo.com/image/151229/rwdkqqrpk.jpg?imageView2/0/w/720/q/85/format/webp"},{"ad_monitors":[],"id":8,"image_url":"http://img02.liwushuo.com/image/151210/enwhfssng.jpg-w720","target_url":"liwushuo:///page?type=topic&topic_id=172","webp_url":"http://img02.liwushuo.com/image/151210/enwhfssng.jpg?imageView2/0/w/720/q/85/format/webp"},{"ad_monitors":[],"id":11,"image_url":"http://img03.liwushuo.com/image/151216/8erc80eje.jpg-w720","target_url":"liwushuo:///page?type=topic&topic_id=174","webp_url":"http://img03.liwushuo.com/image/151216/8erc80eje.jpg?imageView2/0/w/720/q/85/format/webp"},{"ad_monitors":[],"id":4,"image_url":"http://img02.liwushuo.com/image/151210/9kiuo061y.jpg-w720","target_url":"liwushuo:///page?type=topic&topic_id=113","webp_url":"http://img02.liwushuo.com/image/151210/9kiuo061y.jpg?imageView2/0/w/720/q/85/format/webp"}]}
     * message : OK
     */

    private int code;
    private DataEntity data;
    private String message;

    public void setCode(int code) {
        this.code = code;
    }

    public void setData(DataEntity data) {
        this.data = data;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public DataEntity getData() {
        return data;
    }

    public String getMessage() {
        return message;
    }

    public static class DataEntity {
        /**
         * ad_monitors : []
         * id : 27
         * image_url : http://img01.liwushuo.com/image/160317/bpr08shay.jpg-w720
         * target_url : liwushuo:///page?type=post&post_id=1038439&page_action=navigation
         * webp_url : http://img01.liwushuo.com/image/160317/bpr08shay.jpg?imageView2/0/w/720/q/85/format/webp
         */

        private List<SecondaryBannersEntity> secondary_banners;

        public void setSecondary_banners(List<SecondaryBannersEntity> secondary_banners) {
            this.secondary_banners = secondary_banners;
        }

        public List<SecondaryBannersEntity> getSecondary_banners() {
            return secondary_banners;
        }

        public static class SecondaryBannersEntity {
            private int id;
            private String image_url;
            private String target_url;
            private String webp_url;
            private List<?> ad_monitors;

            public void setId(int id) {
                this.id = id;
            }

            public void setImage_url(String image_url) {
                this.image_url = image_url;
            }

            public void setTarget_url(String target_url) {
                this.target_url = target_url;
            }

            public void setWebp_url(String webp_url) {
                this.webp_url = webp_url;
            }

            public void setAd_monitors(List<?> ad_monitors) {
                this.ad_monitors = ad_monitors;
            }

            public int getId() {
                return id;
            }

            public String getImage_url() {
                return image_url;
            }

            public String getTarget_url() {
                return target_url;
            }

            public String getWebp_url() {
                return webp_url;
            }

            public List<?> getAd_monitors() {
                return ad_monitors;
            }
        }
    }
}
