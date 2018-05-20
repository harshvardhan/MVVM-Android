/*
 * Copyright (C) 2017 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.test.dailydomain.martapp.ui.common;

import android.databinding.DataBindingComponent;
import android.databinding.DataBindingUtil;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.test.dailydomain.martapp.R;
import com.test.dailydomain.martapp.databinding.CardProductBinding;
import com.test.dailydomain.martapp.util.Objects;
import com.test.dailydomain.martapp.vo.Product;


/**
 * A RecyclerView adapter for {@link Product} class.
 */
public class ProductListAdapter extends DataBoundListAdapter<Product, CardProductBinding> {
    private final DataBindingComponent dataBindingComponent;
    private final ProductClickCallback productClickCallback;
    private final boolean showFullName;

    public ProductListAdapter(DataBindingComponent dataBindingComponent, boolean showFullName,
                             ProductClickCallback productClickCallback) {
        this.dataBindingComponent = dataBindingComponent;
        this.productClickCallback = productClickCallback;
        this.showFullName = showFullName;
    }

    @Override
    protected CardProductBinding createBinding(ViewGroup parent) {
        CardProductBinding binding = DataBindingUtil
                .inflate(LayoutInflater.from(parent.getContext()), R.layout.card_product,
                        parent, false, dataBindingComponent);
        //binding.setShowFullName(showFullName);
        binding.getRoot().setOnClickListener(v -> {
            Product product = binding.getProduct();
            if (product != null && productClickCallback != null) {
                productClickCallback.onClick(product);
            }
        });
        return binding;
    }

    @Override
    protected void bind(CardProductBinding binding, Product item) {
        binding.setProduct(item);
    }

    @Override
    protected boolean areItemsTheSame(Product oldItem, Product newItem) {
        return Objects.equals(oldItem.getId(), newItem.getId()) &&
                Objects.equals(oldItem.getDesc(), newItem.getDesc());
    }

    @Override
    protected boolean areContentsTheSame(Product oldItem, Product newItem) {
        return Objects.equals(oldItem.getDesc(), newItem.getDesc()) &&
                oldItem.getId() == newItem.getId();
    }

    public interface ProductClickCallback {
        void onClick(Product product);
    }
}
