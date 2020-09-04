package app.ss_n.common_ktx.extension

/**
 * Integer to Decimal Text
 *
 * @return example 10000 to 10,000
 */
fun Long.decimalText(): String {
    return String.format("%,d", this)
}
