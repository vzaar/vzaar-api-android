package com.vzaar.apiclient.sample.categorylist;

import com.vzaar.apiclient.Vzaar;
import com.vzaar.apiclient.VzaarCallback;
import com.vzaar.apiclient.VzaarException;
import com.vzaar.apiclient.model.Category;
import com.vzaar.apiclient.request.GetCategoriesRequest;
import com.vzaar.apiclient.request.GetCategoriesSubtreeRequest;
import com.vzaar.apiclient.response.VzaarListResponse;

import java.util.ArrayList;
import java.util.List;

class CategoryListInteractor {

    private final Vzaar vzaar;

    CategoryListInteractor(Vzaar vzaar) {
        this.vzaar = vzaar;
    }

    void getCategories(Integer categoryId, int page, final GetCategoriesListener listener) {
        if (categoryId == null) {
            getCategoriesRoot(page, listener);
        } else {
            getCategoriesSubtree(categoryId, page, listener);
        }
    }

    private void getCategoriesRoot(int page, final GetCategoriesListener listener) {
        final GetCategoriesRequest request = new GetCategoriesRequest.Builder()
                .setPerPage(5)
                .setPage(page + 1)
                .build();

        vzaar.getCategories(request, new VzaarCallback<VzaarListResponse<Category>>() {
            @Override
            public void onSuccessResponse(VzaarListResponse<Category> response) {
                listener.onLoadCategoriesSuccess(response.getData());

                if (response.isFinalPage()) {
                    listener.onLoadCategoriesEnd();
                }

            }

            @Override
            public void onErrorResponse(VzaarListResponse<Category> response) {
                listener.onLoadCategoriesFailure();
            }

            @Override
            public void onNoResponse(VzaarException e) {
                e.printStackTrace();
                listener.onLoadCategoriesFailure();
            }
        });
    }

    private void getCategoriesSubtree(final int categoryId, int page, final GetCategoriesListener listener) {
        final GetCategoriesSubtreeRequest request = new GetCategoriesSubtreeRequest.Builder(
                categoryId)
                .setPerPage(5)
                .setPage(page + 1)
                .build();

        vzaar.getCategoriesSubtree(request, new VzaarCallback<VzaarListResponse<Category>>() {
            @Override
            public void onSuccessResponse(final VzaarListResponse<Category> response) {
                List<Category> categories = response.getData();
                Category parent = null;

                if (categories.size() > 0) {
                    List<Category> categoriesMinusParent = new ArrayList<>(categories.size() - 1);
                    for (int i = 0; i < categories.size(); i++) {
                        Category category = categories.get(i);
                        if (category.getId() != categoryId) {
                            categoriesMinusParent.add(category);
                        } else {
                            parent = category;
                        }
                    }

                    if (parent != null) {
                        listener.onParentCategoryAvailable(parent);
                    }

                    listener.onLoadCategoriesSuccess(categoriesMinusParent);
                }

                if (response.isFinalPage()) {
                    listener.onLoadCategoriesEnd();
                }
            }

            @Override
            public void onErrorResponse(VzaarListResponse<Category> response) {
                listener.onLoadCategoriesFailure();
            }

            @Override
            public void onNoResponse(VzaarException e) {
                e.printStackTrace();
                listener.onLoadCategoriesFailure();
            }
        });
    }


    interface GetCategoriesListener {

        void onLoadCategoriesFailure();

        void onLoadCategoriesSuccess(List<Category> categories);

        void onLoadCategoriesEnd();

        void onParentCategoryAvailable(Category category);
    }
}
