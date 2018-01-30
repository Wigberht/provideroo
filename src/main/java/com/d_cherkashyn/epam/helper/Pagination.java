package com.d_cherkashyn.epam.helper;

import java.util.HashMap;
import java.util.Map;

public class Pagination {
    private Map<Integer, String> pages = new HashMap<>();
    
    public Pagination() {}
    
    public void addPage(String link, int number) {
        pages.put(number, link);
    }
    
    public Map<Integer, String> getPages() {
        return pages;
    }
    
    public void setPages(Map<Integer, String> pages) {
        this.pages = pages;
    }
    
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Pagination{");
        sb.append("pages=").append(pages);
        sb.append('}');
        return sb.toString();
    }
    
    public class Page {
        public String link;
        public int number;
        
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
