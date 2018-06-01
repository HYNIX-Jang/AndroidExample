package com.hynixlabs.jsonexample;

public class BookItems {
    /**
     * title : 文豪ストレイドッグス DEAD <b>APPLE</b> 公式ガイドブック 煙霧錄
     * link : http://book.naver.com/bookdb/book_detail.php?bid=13396718
     * image : http://bookthumb.phinf.naver.net/cover/133/967/13396718.jpg?type=m1&udate=20180406
     * author : 편집부
     * price : 18560
     * discount :
     * publisher : KADOKAWA
     * pubdate : 20180403
     * isbn : 4041066956 9784041066959
     * description :
     */

    private String title;
    private String image;
    private String author;
    private String price;


    public String getTitle() {
        return title;
    }

    public String getImage() {
        return image;
    }

    public String getAuthor() {
        return author;
    }

    public String getPrice() {
        return price;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
