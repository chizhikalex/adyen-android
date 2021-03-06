/*
 * Copyright (c) 2019 Adyen N.V.
 *
 * This file is open source and available under the MIT license. See the LICENSE file for more info.
 *
 * Created by arman on 12/4/2019.
 */

package com.adyen.checkout.base.models;

import android.arch.lifecycle.ViewModel;

import com.adyen.checkout.base.model.paymentmethods.PaymentMethod;

public class TestViewModelWithConstructor extends ViewModel {

    public PaymentMethod paymentMethod;
    public TestConfiguration testConfiguration;

    public TestViewModelWithConstructor(PaymentMethod paymentMethod, TestConfiguration configuration) {
        this.paymentMethod = paymentMethod;
        this.testConfiguration = configuration;
    }
}
