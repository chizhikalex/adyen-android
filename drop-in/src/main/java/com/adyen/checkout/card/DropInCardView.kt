/*
 * Copyright (c) 2019 Adyen N.V.
 *
 * This file is open source and available under the MIT license. See the LICENSE file for more info.
 *
 * Created by arman on 30/8/2019.
 */

package com.adyen.checkout.card

import android.arch.lifecycle.LifecycleOwner
import android.arch.lifecycle.Observer
import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import com.adyen.checkout.base.api.ImageLoader
import com.adyen.checkout.base.ui.view.AdyenLinearLayout
import com.adyen.checkout.card.data.CardOutputData
import kotlinx.android.synthetic.main.view_card_component_dropin.view.cardView
import kotlinx.android.synthetic.main.view_card_component_dropin.view.recyclerView_cardList
import com.adyen.checkout.dropin.R as dropInR

class DropInCardView : AdyenLinearLayout<CardComponent>, Observer<CardOutputData> {

    lateinit var mCardListAdapter: CardListAdapter

    constructor(context: Context) : this(context, null)

    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        orientation = VERTICAL
        LayoutInflater.from(context).inflate(dropInR.layout.view_card_component_dropin, this, true)
    }

    override fun initView() {
        // nothing
    }

    override fun onComponentAttached() {
        if (!component.isStoredPaymentMethod) {
            mCardListAdapter = CardListAdapter(ImageLoader.getInstance(context, component.configuration.environment),
                component.configuration.supportedCardTypes)
            recyclerView_cardList.adapter = mCardListAdapter
        }
    }

    override fun observeComponentChanges(lifecycleOwner: LifecycleOwner) {
        cardView.attach(component, lifecycleOwner)
        component.observeOutputData(lifecycleOwner, this)
    }

    override fun onChanged(cardOutputData: CardOutputData?) {
        cardOutputData?.let {
            if (!component.isStoredPaymentMethod) {
                mCardListAdapter.setFilteredCard(component.getSupportedFilterCards(it.cardNumberField.value))
            }
        }
    }

    override fun isConfirmationRequired(): Boolean {
        return true
    }
}
