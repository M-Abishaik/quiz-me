package com.extremeplayer.quizme;

import static android.R.attr.id;

/**
 * Created by Abishaik on 30-06-2017.
 */

public class Topics {

    private String textTopics;
    private String Url;

    Topics(String url, String topics) {
        this.Url = url;
        this.textTopics = topics;
    }


    public String getTextTopics() {
        return textTopics;
    }

    public void setTextTopics(String text) {
        textTopics = text;
    }

    public void setUrl(String url)
    {
        Url = url;
    }

    public String getUrl()
    {
        return Url;
    }
}
