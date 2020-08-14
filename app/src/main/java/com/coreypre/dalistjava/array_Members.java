package com.coreypre.dalistjava;

import java.util.ArrayList;

class Member {
    private ArrayList<String> ListArray;

    public Member(ArrayList<String> listArray) {
        ListArray = listArray;
    }

    public void setListArray(ArrayList<String> listArray) {
        ListArray = listArray;
    }

    public ArrayList<String> getListArray() {
        return ListArray;
    }
}
