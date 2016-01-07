package model;

import java.util.Arrays;

/**
 * Created by kelei on 15/12/31.
 */
public class ApiPagingModel<T> {
    public long CurrentPage;
    public long ItmesPerPage;
    public long TotalItems;
    public long TotalPages;
    public T[] Items;

    @Override
    public String toString() {
        return "ApiPagingModel{" +
                "CurrentPage=" + CurrentPage +
                ", ItmesPerPage=" + ItmesPerPage +
                ", TotalItems=" + TotalItems +
                ", TotalPages=" + TotalPages +
                ", Items=" + Arrays.toString(Items) +
                '}';
    }
}
