package utils;

public class Para<T1,T2> {
    public final T1 v1;
    public final T2 v2;

    public Para(T1 v1, T2 v2) {
        this.v1 = v1;
        this.v2 = v2;
    }

    @Override
    public String toString() {
        return "Para{" +
                "v1=" + v1 +
                ", v2=" + v2 +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Para<?, ?> para = (Para<?, ?>) o;

        if (v1 != null ? !v1.equals(para.v1) : para.v1 != null) return false;
        return v2 != null ? v2.equals(para.v2) : para.v2 == null;
    }

    @Override
    public int hashCode() {
        int result = v1 != null ? v1.hashCode() : 0;
        result = 31 * result + (v2 != null ? v2.hashCode() : 0);
        return result;
    }
}