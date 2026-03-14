package com.notte.app.domain.error

/**
 * User-friendly error messages in Turkish.
 */
object ErrorMessages {
    const val STORAGE_FULL = "Cihazınızda yeterli depolama alanı yok. Lütfen yer açın ve tekrar deneyin."
    const val SAVE_FAILED = "Not kaydedilemedi. Tekrar denemek ister misiniz?"
    const val DELETE_FAILED = "Not silinemedi. Lütfen tekrar deneyin."
    const val LOAD_FAILED = "Notlar yüklenemedi. Lütfen uygulamayı yeniden başlatın."
    const val EMPTY_NOTE = "Boş notlar kaydedilemez. Lütfen içerik ekleyin."
    const val UNEXPECTED_ERROR = "Beklenmeyen bir hata oluştu. Lütfen tekrar deneyin."
    const val CONTENT_TOO_LARGE = "Not içeriği çok büyük. Lütfen içeriği kısaltın."
    const val INVALID_NOTE_ID = "Geçersiz not kimliği."
    const val DATABASE_CORRUPTION = "Veritabanı bozulmuş. Lütfen uygulamayı yeniden yükleyin."
    const val CONCURRENCY_CONFLICT = "Eşzamanlı işlem çakışması. Lütfen tekrar deneyin."
}
