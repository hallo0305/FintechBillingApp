package com.industri.fintechbilling

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson

class InvoiceActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_invoice)

        val json = """
            {
                "invoice_number": "INV-2026-FT9012",
                "transaction_date": "2026-09-23 10:15:00",
                "payment_status": "PAID_SETTLED",
                "payment_method": "QRIS_BCA",

                "merchant": {
                    "merchant_id": "MCH-99201",
                    "merchant_name": "Mega Elektrindo Ritel",
                    "city_location": "Bandung",
                    "is_verified": true
                },

                "customer": {
                    "customer_id": "CUST-4412",
                    "full_name": "Ahmad Fauzi",
                    "phone": "081298765432"
                },

                "items": [
                    {
                        "item_id": "ITM-01",
                        "item_name": "Kabel Type-C 65W Fast Charging",
                        "qty": 2,
                        "unit_price": 75000.0,
                        "subtotal": 150000.0
                    },
                    {
                        "item_id": "ITM-02",
                        "item_name": "Adaptor GaN Charger 3-Port 100W",
                        "qty": 1,
                        "unit_price": 320000.0,
                        "subtotal": 320000.0
                    },
                    {
                        "item_id": "ITM-03",
                        "item_name": "Mouse Wireless Silent Click Ergonomis",
                        "qty": 1,
                        "unit_price": 130000.0,
                        "subtotal": 130000.0
                    }
                ],

                "summary": {
                    "subtotal_amount": 600000.0,
                    "discount_amount": 50000.0,
                    "tax_ppn_11": 60500.0,
                    "service_fee": 2500.0,
                    "total_paid": 613000.0
                }
            }
        """.trimIndent()

        // JSON → Object Kotlin
        val invoice = Gson().fromJson(
            json,
            InvoiceResponse::class.java
        )

        // =========================
        // DATA INVOICE
        // =========================

        findViewById<TextView>(R.id.tvInvoiceNo).text =
            invoice.invoiceNumber

        findViewById<TextView>(R.id.tvTransactionDate).text =
            formatTransactionDate(invoice.transactionDate)

        findViewById<TextView>(R.id.tvStatusBadge).text =
            invoice.paymentStatus

        // =========================
        // MERCHANT
        // =========================

        findViewById<TextView>(R.id.tvMerchantName).text =
            invoice.merchant.merchantName

        // =========================
        // CUSTOMER
        // =========================

        findViewById<TextView>(R.id.tvCustomerName).text =
            "Penerima: ${invoice.customer.fullName} (${invoice.customer.phone})"

        // =========================
        // DAFTAR PRODUK
        // =========================

        val recyclerView =
            findViewById<RecyclerView>(R.id.rvInvoiceItems)

        recyclerView.layoutManager =
            LinearLayoutManager(this)

        recyclerView.adapter =
            InvoiceItemAdapter(invoice.items)

        // =========================
        // RINCIAN PEMBAYARAN
        // =========================

        findViewById<TextView>(R.id.tvSubtotalAmt).text =
            formatRupiah(invoice.summary.subtotalAmount)

        findViewById<TextView>(R.id.tvDiscountAmt).text =
            "-" + formatRupiah(invoice.summary.discountAmount)

        findViewById<TextView>(R.id.tvTaxAmt).text =
            formatRupiah(invoice.summary.taxPpn11)

        findViewById<TextView>(R.id.tvFeeAmt).text =
            formatRupiah(invoice.summary.serviceFee)

        findViewById<TextView>(R.id.tvTotalPaid).text =
            formatRupiah(invoice.summary.totalPaid)
    }

    private fun formatRupiah(amount: Double): String {
        return "Rp" + String.format(
            "%,.2f",
            amount
        ).replace(",", "#")
            .replace(".", ",")
            .replace("#", ".")
    }
}