package com.d_cherkashyn.epam.helper;

import java.util.ArrayList;
import java.util.List;

public class Pagination {
    private List<Page> pages = new ArrayList<>();
    
    Pagination() {}
    
    public void addPage(String link, int number) {
        pages.add(new Page(link, number));
    }
    
    public List<Page> getPages() {
        return pages;
    }
    
    public void setPages(List<Page> pages) {
        this.pages = pages;
    }
    
    private class Page {
        String link;
        int number;
        
        public Page(String link, int number) {
            this.link = link;
            this.number = number;
        }
        
        public String getLink() {
            return link;
        }
        
        public void setLink(String link) {
            this.link = link;
        }
        
        public int getNumber() {
            return number;
        }
        
        public void setNumber(int number) {
            this.number = number;
        }
    }
}
