package ss_n.common_ktx.extension

import android.Manifest.permission
import android.content.Context
import android.os.Bundle
import android.security.keystore.KeyGenParameterSpec
import android.security.keystore.KeyProperties
import android.speech.tts.UtteranceProgressListener
import android.util.Base64
import android.widget.Toast
import androidx.annotation.RequiresPermission
import androidx.core.os.bundleOf
import com.squareup.moshi.Moshi.Builder
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import retrofit2.Converter.Factory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import ss_n.common_ktx.HttpClient
import ss_n.common_ktx.TextToSpeechManager
import ss_n.common_ktx.TextToSpeechManager.Error
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import java.io.File
import java.math.BigInteger
import java.security.InvalidAlgorithmParameterException
import java.security.KeyStore
import java.security.NoSuchAlgorithmException
import java.security.NoSuchProviderException
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import javax.crypto.Cipher
import javax.crypto.CipherInputStream
import javax.crypto.CipherOutputStream
import javax.crypto.KeyGenerator
import javax.crypto.SecretKey
import javax.crypto.spec.IvParameterSpec
import javax.security.auth.x500.X500Principal

/**
 * String parse to Date
 *
 * @param pattern default = "yyyy/MM/dd HH:mm:ss"
 * @param locale default = device default
 * @return parsed Date
 */
fun String.toDate(
    pattern: String = "yyyy/MM/dd HH:mm:ss",
    locale: Locale = Locale.getDefault()
): Date? {
    val dateFormat = SimpleDateFormat(pattern, locale)
    return try {
        dateFormat.parse(this)
    } catch (e: ParseException) {
        null
    }
}

/**
 * Crate File from String
 * If there is no directory, create it together
 *
 * @return Created Files
 */
@RequiresPermission(
    allOf = [permission.WRITE_EXTERNAL_STORAGE, permission.READ_EXTERNAL_STORAGE]
)
fun String.makeFile(): File = File(this).apply {
    this.mkdirs()
}

/**
 * Move File
 *
 * @param target targetFilePath
 * @return Moved File
 */
@RequiresPermission(
    allOf = [permission.WRITE_EXTERNAL_STORAGE, permission.READ_EXTERNAL_STORAGE]
)
fun String.moveFile(target: String): File? {
    val preFile = File(target)
    if (!preFile.exists()) return null

    val movedFile = File(this)
    return when (preFile.renameTo(movedFile)) {
        true -> movedFile
        else -> null
    }
}

/**
 * Has File
 *
 * @return true: File exist
 */
@RequiresPermission(
    allOf = [permission.WRITE_EXTERNAL_STORAGE, permission.READ_EXTERNAL_STORAGE]
)
fun String.hasFile(): Boolean {
    if (isEmpty()) return false

    val target = File(this)
    return target.exists() && target.isFile
}

/**
 * Get File Size
 *
 * @param sizeFormat default KB size format
 * @return File Size
 */
@RequiresPermission(
    allOf = [permission.WRITE_EXTERNAL_STORAGE, permission.READ_EXTERNAL_STORAGE]
)
fun String.getFileSize(sizeFormat: Long = 1024): Long {
    if (isEmpty()) return 0

    val target = File(this)

    if (!target.exists() || !target.isFile) return 0

    return target.length() / sizeFormat
}

/**
 * Regex String
 *
 * @param pattern regex pattern
 * @param errorMessage error message
 * @param error response method
 * @return true:matched
 */
fun String.matchPattern(
    pattern: String,
    errorMessage: String = "",
    error: ((String) -> Unit?)? = null
): Boolean {
    return pattern.toRegex().matchEntire(this)?.let {
        true
    } ?: run {
        error?.let {
            it(errorMessage)
        }
        false
    }
}

/**
 * Matched MailAddress
 *
 * @param errorMessage error message
 * @param error response method
 * @return true:matched
 */
fun String.isMailAddress(
    errorMessage: String = "",
    error: ((String) -> Unit?)? = null
): Boolean {
    val pattern = "^[a-zA-Z0-9\\._\\-\\+]+@[a-zA-Z0-9_\\-]+\\.[a-zA-Z\\.]+[a-zA-Z]\$"
    return matchPattern(pattern, errorMessage, error)
}

/**
 * Matched PhoneNumber
 *
 * @param errorMessage error message
 * @param error response method
 * @return true:matched
 */
fun String.isPhoneNumber(errorMessage: String = "", error: ((String) -> Unit?)? = null): Boolean {
    val pattern = "\\d{10}|\\d{11}"
    return matchPattern(pattern, errorMessage, error)
}

/**
 * Matched Number
 *
 * @param errorMessage error message
 * @param error response method
 * @return true:matched
 */
fun String.isNumber(errorMessage: String = "", error: ((String) -> Unit?)? = null): Boolean {
    val pattern = "^[0-9]+\$"
    return matchPattern(pattern, errorMessage, error)
}

/**
 * Matched Int
 *
 * @param errorMessage error message
 * @param error response method
 * @return true:matched
 */
fun String.isInt(errorMessage: String = "", error: ((String) -> Unit?)? = null): Boolean {
    val pattern = "^[0-9]{1,11}\$"
    return matchPattern(pattern, errorMessage, error)
}

/**
 * Partial Matched Japanese
 *
 * @param errorMessage error message
 * @param error response method
 * @return true:partial matched
 */
fun String.hasJapanese(errorMessage: String = "", error: ((String) -> Unit?)? = null): Boolean {
    val pattern = "[^0-9a-zA-Zぁ-んァ-ヶ一-龠々ー]"
    return matchPattern(pattern, errorMessage, error)
}

/**
 * Show Toast
 *
 * @param context Context
 * @param toastLength Toast Length
 */
fun String.showToast(context: Context, toastLength: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(context, this, toastLength).show()
}

/**
 * Create Bundle
 *
 * @param value value
 * @return [Bundle]
 */
fun String.toBundleKey(value: Any): Bundle = bundleOf(this to value)

/**
 * Create MutableMap
 *
 * @param value value
 * @return [MutableMap]
 */
fun String.toMutableMapKey(value: Any): MutableMap<String, Any> = mutableMapOf(this to value)

/**
 * Create Map
 *
 * @param value value
 * @return [Map]
 */
fun String.toMapKey(value: Any): Map<String, Any> = mapOf(this to value)

/**
 * Create Retrofit Service
 *
 * @param T Service interface class
 * @param service Service interface class
 * @param client OkHttpClient :default [HttpClient]
 * @param converterFactory Json Parser Converter Factory :default [MoshiConverterFactory]
 * @return Service interface class Instance
 */
fun <T> String.createRetrofitService(
    service: Class<T>,
    client: OkHttpClient? = HttpClient.defaultClient,
    converterFactory: Factory? = MoshiConverterFactory.create(
        Builder().add(KotlinJsonAdapterFactory()).build()
    )
): T {
    return Retrofit.Builder().apply {
        baseUrl(this@createRetrofitService)
        client?.also { client(it) }
        converterFactory?.also { addConverterFactory(it) }
    }.build().create(service)
}

/**
 * Text To Speech
 *
 * @param context Context
 * @param utteranceProgressListener UtteranceProgressListener
 * @param pitchHeight pitch height : default 1.0
 * @param pitchRate pitch rate : default 1.0
 * @return
 */
fun String.toSpeech(
    context: Context,
    utteranceProgressListener: UtteranceProgressListener? = null,
    pitchHeight: Float = 1.0f,
    pitchRate: Float = 1.0f
): Error {
    val ttsManager = TextToSpeechManager
    ttsManager.context = context
    return ttsManager.speech(this, utteranceProgressListener, pitchHeight, pitchRate)
}

fun String.encrypt(
    alias: String,
    ivKey: String = "",
    keyProvider: String = "AndroidKeyStore",
    isPrintError: Boolean = false
): String? {
    return try {
        val keyStore = KeyStore.getInstance(keyProvider)
        keyStore.load(null)
        if (!keyStore.containsAlias(alias)) {
            createAESKey(alias, keyProvider, isPrintError)
        }
        val secretKey: SecretKey = keyStore.getKey(alias, null) as SecretKey
        val cipher = Cipher.getInstance(CIPHER_TRANSFORMATION_AES)
        val ivParameterSpec = IvParameterSpec(ivKey.toByteArray())
        cipher.init(Cipher.DECRYPT_MODE, secretKey, ivParameterSpec)
        val byteArrayOutputStream = ByteArrayOutputStream()
        val cipherOutputStream = CipherOutputStream(byteArrayOutputStream, cipher)
        cipherOutputStream.write(this.toByteArray())
        cipherOutputStream.close()
        Base64.encodeToString(byteArrayOutputStream.toByteArray(), Base64.NO_WRAP)
    } catch (e: Exception) {
        if (isPrintError) {
            e.printStackTrace()
        }
        null
    }
}

fun String.decrypt(
    alias: String,
    ivKey: String = "",
    keyProvider: String = "AndroidKeyStore",
    isPrintError: Boolean = false
): String? {
    return try {
        val keyStore = KeyStore.getInstance(keyProvider)
        keyStore.load(null)
        val secretKey: SecretKey = keyStore.getKey(alias, null) as SecretKey? ?: return null

        val cipher = Cipher.getInstance(CIPHER_TRANSFORMATION_AES)
        val ivParameterSpec = IvParameterSpec(ivKey.toByteArray())
        cipher.init(Cipher.DECRYPT_MODE, secretKey, ivParameterSpec)

        val cipherInputStream = CipherInputStream(
            ByteArrayInputStream(Base64.decode(this, Base64.NO_WRAP)),
            cipher
        )
        val byteArrayOutputStream = ByteArrayOutputStream()
        var buffer: Int
        do {
            buffer = cipherInputStream.read()
            if (buffer == -1) break
            byteArrayOutputStream.write(buffer)
        } while (true)
        byteArrayOutputStream.close()
        byteArrayOutputStream.toString("UTF-8")
    } catch (e: Exception) {
        if (isPrintError) {
            e.printStackTrace()
        }
        null
    }
}

private const val CIPHER_TRANSFORMATION_AES =
    "${KeyProperties.KEY_ALGORITHM_AES}/" +
        "${KeyProperties.BLOCK_MODE_CBC}/${KeyProperties.ENCRYPTION_PADDING_PKCS7}"

private fun createAESKey(
    alias: String,
    keyProvider: String,
    isPrintError: Boolean = false
) {
    runCatching {
        val keyGenerator = KeyGenerator.getInstance(KeyProperties.KEY_ALGORITHM_AES, keyProvider)
        keyGenerator.init(
            KeyGenParameterSpec.Builder(
                alias,
                KeyProperties.PURPOSE_ENCRYPT or KeyProperties.PURPOSE_DECRYPT
            )
                .setCertificateSubject(X500Principal("CN=$alias"))
                .setCertificateSerialNumber(BigInteger.ONE)
                .setBlockModes(KeyProperties.BLOCK_MODE_CBC)
                .setEncryptionPaddings(KeyProperties.ENCRYPTION_PADDING_PKCS7)
                .setRandomizedEncryptionRequired(false)
                .build()
        )

        keyGenerator.generateKey()
    }.exceptionOrNull()?.let {
        when (it) {
            is NoSuchProviderException,
            is NoSuchAlgorithmException,
            is InvalidAlgorithmParameterException -> {
                if (!isPrintError) return
                it.printStackTrace()
            }
        }
    }
}
