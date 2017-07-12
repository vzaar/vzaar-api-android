package com.vzaar.apiclient.response;

import java.util.List;

@SuppressWarnings({"CanBeFinal", "unused"})
public class VzaarListResponse<U> extends VzaarResponse<List<U>> {

    private Meta meta;

    public boolean isFinalPage() {
        return
                meta == null ||
                        meta.links == null ||
                        meta.links.next == null;
    }

    @SuppressWarnings({"CanBeFinal", "unused"})
    private static class Meta {
        private int totalCount;
        private Links links;

        @SuppressWarnings({"CanBeFinal", "unused"})
        private static class Links {
            private String first;
            private String previous;
            private String next;
            private String last;
        }
    }

}