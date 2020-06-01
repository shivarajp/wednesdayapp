
package com.shivaraj.wednesdayapp.data.models;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Data {

    @SerializedName("autoCompleteRequestString")
    @Expose
    private String autoCompleteRequestString;
    @SerializedName("focusWord")
    @Expose
    private String focusWord;
    @SerializedName("addressList")
    @Expose
    private List<AddressList> addressList = null;

    public String getAutoCompleteRequestString() {
        return autoCompleteRequestString;
    }

    public void setAutoCompleteRequestString(String autoCompleteRequestString) {
        this.autoCompleteRequestString = autoCompleteRequestString;
    }

    public String getFocusWord() {
        return focusWord;
    }

    public void setFocusWord(String focusWord) {
        this.focusWord = focusWord;
    }

    public List<AddressList> getAddressList() {
        return addressList;
    }

    public void setAddressList(List<AddressList> addressList) {
        this.addressList = addressList;
    }

}
