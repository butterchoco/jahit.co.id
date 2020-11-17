package id.ac.ui.cs.mobileprogramming.ahmadsupriyanto.jahitcoid;

import java.text.NumberFormat
import java.util.*

class Project {
    var name: String;
    var price: String;
    var amount: String;
    var status: String;
    var annotation: String;

    constructor(name: String, price: Int, amount: Int, status: String, annotation: String) {
        this.name = name;
        this.status = status;
        this.annotation = annotation;
        this.price = this.changeCurrencyFormat(price)
        this.amount = this.changeCurrencyFormat(amount);
    }

    fun changeCurrencyFormat(value: Int): String {
        val format: NumberFormat = NumberFormat.getCurrencyInstance();
        format.setMaximumFractionDigits(0);
        format.setCurrency(Currency.getInstance("IDR"));
        return format.format(value);
    }

}