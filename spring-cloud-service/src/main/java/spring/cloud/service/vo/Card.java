package spring.cloud.service.vo;

import java.io.Serializable;

public class Card implements Serializable,Cloneable {

    private static final long serialVersionUID = 2199849472126901511L;
    private String cardName;

    public String getCardName() {
        return cardName;
    }

    public void setCardName(String cardName) {
        this.cardName = cardName;
    }

    @Override
    public String toString() {
        return "Card{" +
                "cardName='" + cardName + '\'' +
                '}';
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
