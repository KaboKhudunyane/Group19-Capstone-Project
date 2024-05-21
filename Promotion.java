package za.ac.cput.domain;

import java.io.Serializable;
import java.util.*;

public class Promotion implements Serializable, List<Car> {
    private String promotionID;
    private String description;
    private String startDate;
    private String endDate;
    private String discount;

    protected Promotion() {}

    private Promotion(Builder builder) {
        this.promotionID = builder.promotionID;
        this.description = builder.description;
        this.startDate = builder.startDate;
        this.endDate = builder.endDate;
        this.discount = builder.discount;
    }

    public String getPromotionID() {
        return promotionID;
    }

    public String getDescription() {
        return description;
    }

    public String getStartDate() {
        return startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public String getDiscount() {
        return discount;
    }

    public void setPromotionID(String promotionID) {
        this.promotionID = promotionID;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public boolean contains(Object o) {
        return false;
    }

    @Override
    public Iterator<Car> iterator() {
        return null;
    }

    @Override
    public Object[] toArray() {
        return new Object[0];
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return null;
    }

    @Override
    public boolean add(Car car) {
        return false;
    }

    @Override
    public boolean remove(Object o) {
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean addAll(Collection<? extends Car> c) {
        return false;
    }

    @Override
    public boolean addAll(int index, Collection<? extends Car> c) {
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return false;
    }

    @Override
    public void clear() {

    }

    @Override
    public boolean equals(Object promotionObject) {
        if (this == promotionObject) return true;
        if (promotionObject == null || getClass() != promotionObject.getClass()) return false;
        Promotion promotion = (Promotion) promotionObject;
        return Objects.equals(promotionID, promotion.promotionID) &&
                Objects.equals(description, promotion.description) &&
                Objects.equals(startDate, promotion.startDate) &&
                Objects.equals(endDate, promotion.endDate) &&
                Objects.equals(discount, promotion.discount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(promotionID, description, startDate, endDate, discount);
    }

    @Override
    public Car get(int index) {
        return null;
    }

    @Override
    public Car set(int index, Car element) {
        return null;
    }

    @Override
    public void add(int index, Car element) {

    }

    @Override
    public Car remove(int index) {
        return null;
    }

    @Override
    public int indexOf(Object o) {
        return 0;
    }

    @Override
    public int lastIndexOf(Object o) {
        return 0;
    }

    @Override
    public ListIterator<Car> listIterator() {
        return null;
    }

    @Override
    public ListIterator<Car> listIterator(int index) {
        return null;
    }

    @Override
    public List<Car> subList(int fromIndex, int toIndex) {
        return null;
    }

    @Override
    public String toString() {
        return "Promotion{" +
                "promotionID='" + promotionID + '\'' +
                ", description='" + description + '\'' +
                ", startDate='" + startDate + '\'' +
                ", endDate='" + endDate + '\'' +
                ", discount='" + discount + '\'' +
                '}';
    }

    public static class Builder {
        private String promotionID;
        private String description;
        private String startDate;
        private String endDate;
        private String discount;

        public Builder setPromotionID(String promotionID) {
            this.promotionID = promotionID;
            return this;
        }

        public Builder setDescription(String description) {
            this.description = description;
            return this;
        }

        public Builder setStartDate(String startDate) {
            this.startDate = startDate;
            return this;
        }

        public Builder setEndDate(String endDate) {
            this.endDate = endDate;
            return this;
        }

        public Builder setDiscount(String discount) {
            this.discount = discount;
            return this;
        }

        public Builder copyPromotion(Promotion promotion) {
            this.promotionID = promotion.promotionID;
            this.description = promotion.description;
            this.startDate = promotion.startDate;
            this.endDate = promotion.endDate;
            this.discount = promotion.discount;
            return this;
        }

        public Promotion buildPromotion() {
            return new Promotion(this);
        }
    }
}

