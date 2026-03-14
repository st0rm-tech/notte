# Uygulama Planı: Notte Android Uygulaması

## Genel Bakış

Bu uygulama planı, macOS'tan ilham alan minimalist not alma uygulaması Notte'nin Android platformu için geliştirilmesini kapsar. Uygulama, Jetpack Compose, Room Database, Hilt ve MVVM + Clean Architecture kullanarak modern Android geliştirme uygulamalarını takip eder.

## Görevler

- [x] 1. Proje yapısını ve temel bağımlılıkları kur
  - Android Studio projesi oluştur (Minimum SDK 24, Target SDK 34)
  - build.gradle.kts dosyalarını yapılandır (Compose, Room, Hilt, Kotest)
  - Proguard kurallarını ekle
  - Clean Architecture için paket yapısını oluştur (data, domain, presentation)
  - _Gereksinimler: Tüm gereksinimler için temel_

- [x] 2. Domain katmanını uygula
  - [x] 2.1 Note entity ve iş mantığını oluştur
    - Note data class'ını tanımla (id, content, createdAt, updatedAt)
    - isValid(), getTitle(), getPreview() metodlarını uygula
    - _Gereksinimler: 1.5, 1.6, 3.2, 3.3_
  
  - [x] 2.2 Repository interface'ini tanımla
    - NoteRepository interface'ini oluştur
    - getAllNotes(), getNoteById(), insertNote(), updateNote(), deleteNote(), searchNotes() metodlarını tanımla
    - _Gereksinimler: 7.1, 7.2_
  
  - [x] 2.3 Use case'leri uygula
    - CreateNoteUseCase oluştur (boş not kontrolü ile)
    - UpdateNoteUseCase oluştur (zaman damgası güncelleme ile)
    - DeleteNoteUseCase oluştur
    - GetAllNotesUseCase oluştur (ters kronolojik sıralama ile)
    - SearchNotesUseCase oluştur
    - _Gereksinimler: 1.5, 1.6, 2.4, 3.1, 8.2_
  
  - [ ]* 2.4 Domain katmanı için özellik testleri yaz
    - **Özellik 1: Geçerli Not Kalıcılığı**
    - **Doğrular: Gereksinimler 1.5, 7.5**
    - **Özellik 2: Boş Not Reddi**
    - **Doğrular: Gereksinimler 1.6**

- [x] 3. Data katmanını uygula
  - [x] 3.1 Room veritabanı varlıklarını ve DAO'yu oluştur
    - NoteEntity data class'ını @Entity ile tanımla
    - İndeksler ekle (updated_at, content)
    - NoteDao interface'ini oluştur (CRUD ve arama sorguları)
    - NotteDatabase abstract class'ını oluştur
    - _Gereksinimler: 7.1, 7.2, 8.2_
  
  - [x] 3.2 Repository implementasyonunu yaz
    - NoteRepositoryImpl class'ını oluştur
    - Domain ve Data katmanları arası mapper fonksiyonları (toDomain, toEntity)
    - Flow kullanarak reaktif veri akışı sağla
    - IO Dispatcher ile asenkron işlemleri yönet
    - _Gereksinimler: 7.1, 7.3, 7.6_
  
  - [x] 3.3 Hata işleme sınıflarını oluştur
    - StorageError sealed class (InsufficientSpace, DatabaseCorruption, WriteFailure, ReadFailure)
    - ValidationError sealed class (EmptyContent, ContentTooLarge, InvalidNoteId)
    - SystemError sealed class (OutOfMemory, UnexpectedCrash, ConcurrencyConflict)
    - ErrorMessages object (Türkçe hata mesajları)
    - _Gereksinimler: 12.1, 12.2, 12.3, 12.6_
  
  - [ ]* 3.4 Data katmanı için özellik testleri yaz
    - **Özellik 1: Geçerli Not Kalıcılığı**
    - **Doğrular: Gereksinimler 1.5, 7.5**
    - **Özellik 14: Eşzamanlılık Güvenliği**
    - **Doğrular: Gereksinimler 7.6**

- [ ] 4. Checkpoint - Temel mimari tamamlandı
  - Tüm testlerin geçtiğinden emin ol, sorular varsa kullanıcıya sor.

- [x] 5. Hilt bağımlılık enjeksiyonunu yapılandır
  - [x] 5.1 Hilt modüllerini oluştur
    - @HiltAndroidApp ile Application class'ı oluştur
    - DatabaseModule oluştur (NotteDatabase ve NoteDao sağla)
    - RepositoryModule oluştur (NoteRepository binding)
    - DispatchersModule oluştur (IO ve Default dispatcher'lar)
    - _Gereksinimler: Tüm gereksinimler için DI_
  
  - [x] 5.2 AndroidManifest.xml'i yapılandır
    - Application class'ını kaydet
    - Minimum izinleri ekle (VIBRATE)
    - Güvenlik ayarlarını yapılandır (allowBackup=false, usesCleartextTraffic=false)
    - _Gereksinimler: 6.2, 12.1_

- [x] 6. Presentation katmanını uygula - ViewModels
  - [x] 6.1 NoteListViewModel'i oluştur
    - @HiltViewModel ile ViewModel tanımla
    - NoteListUiState sealed class (Loading, Empty, Success, Error)
    - StateFlow ile UI state yönetimi
    - Arama sorgusu için StateFlow
    - observeNotes() ile not listesini gözlemle (ters kronolojik sıralama)
    - onSearchQueryChanged() ile arama filtreleme
    - deleteNote() ile not silme
    - _Gereksinimler: 3.1, 3.4, 4.2, 8.2, 8.6_
  
  - [x] 6.2 NoteEditorViewModel'i oluştur
    - @HiltViewModel ile ViewModel tanımla
    - NoteEditorUiState sealed class (Loading, Creating, Editing, Error)
    - StateFlow ile içerik ve UI state yönetimi
    - loadNote() ile mevcut notu yükle
    - setupAutoSave() ile 2 saniye debounce otomatik kaydetme
    - onContentChanged() ile içerik değişikliklerini yakala
    - onBackPressed() ile geri giderken kaydet
    - _Gereksinimler: 1.4, 2.1, 2.2, 2.3, 2.4, 2.5_
  
  - [ ]* 6.3 ViewModel'ler için özellik testleri yaz
    - **Özellik 7: Ters Kronolojik Sıralama**
    - **Doğrular: Gereksinimler 3.1**
    - **Özellik 15: Arama Filtreleme**
    - **Doğrular: Gereksinimler 8.2, 8.3**
    - **Özellik 3: Otomatik Kaydetme Mekanizması**
    - **Doğrular: Gereksinimler 1.4, 2.3, 7.3**

- [x] 7. Theme sistemini uygula
  - [x] 7.1 Renk paletini oluştur
    - NotteColors object (Primary, Secondary, Tertiary, Background)
    - Açık mod renkleri (LightSurface, LightOnSurface, LightOnSurfaceVariant)
    - Koyu mod renkleri (DarkPrimary, DarkSecondary, DarkTertiary, DarkSurface)
    - _Gereksinimler: 5.1, 5.2, 5.3, 5.4, 5.8_
  
  - [x] 7.2 Tipografi ve şekilleri tanımla
    - NotteTypography (displayLarge, titleLarge, titleMedium, bodyLarge, bodyMedium, labelSmall)
    - NotteShapes (small=8dp, medium=12dp, large=16dp)
    - _Gereksinimler: 5.6, 5.7_
  
  - [x] 7.3 NotteTheme Composable'ını oluştur
    - Koyu/açık mod desteği ile MaterialTheme wrapper
    - lightColorScheme ve darkColorScheme yapılandırması
    - _Gereksinimler: 5.8_
  
  - [ ]* 7.4 Tema için özellik testleri yaz
    - **Özellik 23: Kontrast Oranları**
    - **Doğrular: Gereksinimler 11.3**

- [x] 8. Animasyon sistemini uygula
  - [x] 8.1 Animasyon sabitlerini ve geçişlerini tanımla
    - AnimationConstants object (FAST=150ms, NORMAL=250ms, SLOW=300ms)
    - NotteAnimations object (navigationEnterTransition, navigationExitTransition, itemEnterAnimation, itemExitAnimation)
    - FastOutSlowInEasing ve LinearOutSlowInEasing kullan
    - _Gereksinimler: 6.1, 6.3, 6.4, 6.5, 6.6_
  
  - [x] 8.2 Dokunsal geri bildirim modifier'ını oluştur
    - notteClickable() Modifier extension fonksiyonu
    - HapticFeedback entegrasyonu
    - _Gereksinimler: 6.2_
  
  - [ ]* 8.3 Animasyon performansı için özellik testleri yaz
    - **Özellik 12: Dokunsal Geri Bildirim**
    - **Doğrular: Gereksinimler 6.2**

- [ ] 9. Checkpoint - Temel sistemler hazır
  - Tüm testlerin geçtiğinden emin ol, sorular varsa kullanıcıya sor.

- [x] 10. UI bileşenlerini uygula - Not Listesi
  - [x] 10.1 NoteCard Composable'ını oluştur
    - Card ile yuvarlatılmış köşeler (12dp)
    - Note başlığı, önizleme ve zaman damgası göster
    - combinedClickable ile tıklama ve uzun basma desteği
    - DeleteConfirmationDialog ile silme onayı
    - remember ile performans optimizasyonu
    - _Gereksinimler: 3.2, 3.3, 3.4, 4.1, 5.6_
  
  - [x] 10.2 NoteList Composable'ını oluştur
    - LazyVerticalGrid ile liste görünümü
    - Responsive düzen (600dp altında 1 sütun, üstünde 2 sütun)
    - animateItemPlacement ile silme animasyonu
    - Kararlı key kullanımı (note.id)
    - _Gereksinimler: 3.1, 3.6, 4.3, 9.2, 9.3_
  
  - [x] 10.3 NoteListTopBar Composable'ını oluştur
    - Arama TextField'ı
    - Gerçek zamanlı arama filtreleme
    - Arama temizleme butonu
    - _Gereksinimler: 8.1, 8.2, 8.6_
  
  - [x] 10.4 EmptyState ve LoadingIndicator Composable'larını oluştur
    - Boş durum için oluşturma istemi
    - Yükleme göstergesi
    - _Gereksinimler: 3.5_
  
  - [x] 10.5 NoteListScreen Composable'ını oluştur
    - Scaffold ile layout yapısı
    - FloatingActionButton ile not oluşturma
    - UI state'e göre içerik gösterimi (Loading, Empty, Success, Error)
    - ViewModel entegrasyonu
    - _Gereksinimler: 1.1, 3.1, 3.5_
  
  - [ ]* 10.6 Not listesi için UI testleri yaz
    - **Özellik 9: Zaman Damgası Görünürlüğü**
    - **Doğrular: Gereksinimler 3.4**
    - **Özellik 10: Silme İşlemi Bütünlüğü**
    - **Doğrular: Gereksinimler 4.2**

- [x] 11. UI bileşenlerini uygula - Not Editörü
  - [x] 11.1 NoteEditorTopBar Composable'ını oluştur
    - Geri butonu
    - Otomatik kaydetme göstergesi (opsiyonel)
    - _Gereksinimler: 1.4, 2.3_
  
  - [x] 11.2 NoteEditorScreen Composable'ını oluştur
    - BasicTextField ile not içeriği düzenleme
    - FocusRequester ile otomatik odaklanma
    - Placeholder metin ("Notunuzu buraya yazın...")
    - BackHandler ile geri giderken kaydetme
    - ViewModel entegrasyonu
    - _Gereksinimler: 1.2, 1.3, 2.1, 2.2, 2.3_
  
  - [ ]* 11.3 Not editörü için UI testleri yaz
    - **Özellik 4: Not Yükleme Bütünlüğü**
    - **Doğrular: Gereksinimler 2.1, 2.2**
    - **Özellik 5: Zaman Damgası Güncelleme**
    - **Doğrular: Gereksinimler 2.4**

- [x] 12. Navigasyon sistemini uygula
  - [x] 12.1 NotteNavigation Composable'ını oluştur
    - NavHost ile navigasyon yapısı
    - "note_list" ve "note_editor/{noteId}" rotaları
    - Geçiş animasyonları (slideInHorizontally, slideOutHorizontally, 250ms)
    - _Gereksinimler: 1.1, 6.1_
  
  - [x] 12.2 MainActivity'yi oluştur
    - @AndroidEntryPoint ile Hilt entegrasyonu
    - ComponentActivity ile Compose setContent
    - NotteTheme ve NotteNavigation çağrısı
    - _Gereksinimler: Tüm UI gereksinimleri_

- [ ] 13. Checkpoint - UI tamamlandı
  - Tüm testlerin geçtiğinden emin ol, sorular varsa kullanıcıya sor.

- [x] 14. Erişilebilirlik özelliklerini uygula
  - [x] 14.1 İçerik açıklamalarını ekle
    - Tüm etkileşimli öğeler için contentDescription
    - Anlamlı açıklamalar (Türkçe)
    - _Gereksinimler: 11.1_
  
  - [x] 14.2 TalkBack desteğini yapılandır
    - Mantıksal odak sırası
    - Semantik özellikler
    - _Gereksinimler: 11.2_
  
  - [x] 14.3 Minimum dokunma hedefi boyutlarını garanti et
    - Tüm etkileşimli öğeler için 48dp minimum
    - _Gereksinimler: 9.5, 11.5_
  
  - [x] 14.4 Dinamik font boyutlandırma desteği ekle
    - Sistem font boyutu ayarlarına uyum
    - _Gereksinimler: 9.6, 11.4_
  
  - [x] 14.5 Klavye navigasyonu desteği ekle
    - Harici klavye ile navigasyon
    - _Gereksinimler: 11.6_
  
  - [ ]* 14.6 Erişilebilirlik için özellik testleri yaz
    - **Özellik 21: İçerik Açıklamaları**
    - **Doğrular: Gereksinimler 11.1**
    - **Özellik 19: Minimum Dokunma Hedefi**
    - **Doğrular: Gereksinimler 9.5, 11.5**
    - **Özellik 20: Dinamik Font Boyutlandırma**
    - **Doğrular: Gereksinimler 9.6, 11.4**

- [ ] 15. Performans optimizasyonlarını uygula
  - [ ] 15.1 Compose performans optimizasyonları
    - remember ile gereksiz yeniden hesaplamaları önle
    - derivedStateOf kullan
    - Kararlı key'ler ile LazyColumn optimizasyonu
    - _Gereksinimler: 10.2, 10.5_
  
  - [ ] 15.2 Veritabanı optimizasyonları
    - İndeksler ekle (updated_at, content)
    - Paging 3 entegrasyonu (1000+ not için)
    - _Gereksinimler: 10.2_
  
  - [ ] 15.3 Bellek yönetimi
    - LazyColumn geri dönüşüm optimizasyonu
    - contentType kullanımı
    - _Gereksinimler: 10.6_
  
  - [ ]* 15.4 Performans testleri yaz
    - Başlatma süresi testi (<1s)
    - Not yükleme testi (<500ms)
    - Navigasyon testi (<100ms)
    - 60 FPS animasyon testi
    - _Gereksinimler: 10.1, 10.2, 10.5, 7.2, 1.1_

- [ ] 16. Arama işlevselliğini uygula
  - [ ] 16.1 Arama UI'sını entegre et
    - NoteListTopBar'a arama TextField'ı ekle
    - Gerçek zamanlı filtreleme
    - _Gereksinimler: 8.1, 8.2_
  
  - [ ] 16.2 Arama vurgulama özelliğini ekle
    - Eşleşen metni vurgula
    - AnnotatedString kullan
    - _Gereksinimler: 8.4_
  
  - [ ] 16.3 Boş arama sonucu durumunu işle
    - "Sonuç bulunamadı" mesajı
    - _Gereksinimler: 8.5_
  
  - [ ]* 16.4 Arama için özellik testleri yaz
    - **Özellik 15: Arama Filtreleme**
    - **Doğrular: Gereksinimler 8.2, 8.3**
    - **Özellik 16: Arama Vurgulama**
    - **Doğrular: Gereksinimler 8.4**
    - **Özellik 17: Arama Temizleme**
    - **Doğrular: Gereksinimler 8.6**

- [ ] 17. Hata işleme ve kurtarma mekanizmalarını uygula
  - [ ] 17.1 Depolama hatası işleme
    - Yetersiz alan kontrolü
    - Otomatik yeniden deneme (1 kez)
    - Kullanıcı dostu hata mesajları (Türkçe)
    - _Gereksinimler: 12.1, 12.2_
  
  - [ ] 17.2 Hata günlüğü sistemi
    - CrashReporter object (Firebase Crashlytics entegrasyonu opsiyonel)
    - logError() ve logNonFatalError() metodları
    - _Gereksinimler: 12.3_
  
  - [ ] 17.3 Çökme kurtarma
    - Veri bütünlüğü koruması
    - Zarif degradasyon
    - _Gereksinimler: 12.4_
  
  - [ ] 17.4 Giriş doğrulama
    - InputValidator object
    - sanitizeNoteContent() ve validateNoteContent()
    - Anında geri bildirim
    - _Gereksinimler: 12.6_
  
  - [ ]* 17.5 Hata işleme için özellik testleri yaz
    - **Özellik 11: Silme Hatası Kurtarma**
    - **Doğrular: Gereksinimler 4.5**
    - **Özellik 13: Depolama Hatası İşleme**
    - **Doğrular: Gereksinimler 7.4, 12.1**
    - **Özellik 28: Giriş Doğrulama**
    - **Doğrular: Gereksinimler 12.6**

- [ ] 18. Checkpoint - Tüm özellikler tamamlandı
  - Tüm testlerin geçtiğinden emin ol, sorular varsa kullanıcıya sor.

- [ ] 19. Entegrasyon testlerini yaz
  - [ ]* 19.1 Uçtan uca not yaşam döngüsü testi
    - Not oluşturma, düzenleme, kaydetme, silme akışı
    - NoteFlowIntegrationTest
    - _Gereksinimler: 1.1-1.6, 2.1-2.5, 4.1-4.5_
  
  - [ ]* 19.2 Arama akışı entegrasyon testi
    - Arama, filtreleme, temizleme akışı
    - _Gereksinimler: 8.1-8.6_
  
  - [ ]* 19.3 Hata kurtarma entegrasyon testi
    - Depolama hatası, yeniden deneme, kurtarma akışı
    - _Gereksinimler: 12.1-12.6_

- [-] 20. Güvenlik özelliklerini uygula
  - [x] 20.1 ProGuard/R8 konfigürasyonu
    - proguard-rules.pro dosyası
    - Room, Hilt, Coroutines, Compose kuralları
    - _Gereksinimler: Güvenlik_
  
  - [x] 20.2 Manifest güvenlik ayarları
    - allowBackup=false
    - usesCleartextTraffic=false
    - Minimum izinler (VIBRATE)
    - _Gereksinimler: Güvenlik_
  
  - [ ] 20.3 Veritabanı şifreleme (opsiyonel)
    - SQLCipher entegrasyonu
    - _Gereksinimler: Güvenlik (gelecek)_

- [-] 21. Build varyantlarını ve dağıtım yapılandırmasını oluştur
  - [x] 21.1 Build varyantlarını yapılandır
    - Debug ve Release build types
    - Free ve Pro flavor'ları (gelecek için)
    - _Gereksinimler: Dağıtım_
  
  - [x] 21.2 Versiyonlama stratejisi
    - Semantic Versioning (1.0.0)
    - versionCode ve versionName
    - _Gereksinimler: Dağıtım_
  
  - [x] 21.3 App Bundle optimizasyonu
    - Language, density, ABI split'leri
    - _Gereksinimler: Dağıtım_
  
  - [ ] 21.4 Signing konfigürasyonu
    - Release keystore
    - Signing config
    - _Gereksinimler: Dağıtım_

- [-] 22. Dokümantasyon ve son kontroller
  - [x] 22.1 README.md oluştur
    - Proje açıklaması
    - Kurulum talimatları
    - Mimari genel bakış
    - _Gereksinimler: Dokümantasyon_
  
  - [x] 22.2 KDoc yorumları ekle
    - Public API'ler için dokümantasyon
    - _Gereksinimler: Dokümantasyon_
  
  - [ ] 22.3 Son test geçişi
    - Tüm birim testleri
    - Tüm özellik testleri
    - Tüm UI testleri
    - Tüm entegrasyon testleri
    - _Gereksinimler: Tüm_
  
  - [ ] 22.4 Manuel test senaryoları
    - Farklı cihazlarda test (telefon, tablet)
    - Farklı Android sürümlerinde test (API 24-34)
    - Koyu/açık mod test
    - Erişilebilirlik test (TalkBack)
    - _Gereksinimler: Tüm_

- [ ] 23. Final checkpoint - Uygulama tamamlandı
  - Tüm testlerin geçtiğinden emin ol, uygulamanın tüm gereksinimleri karşıladığını doğrula.

## Notlar

- `*` ile işaretlenmiş görevler opsiyoneldir ve daha hızlı MVP için atlanabilir
- Her görev, izlenebilirlik için belirli gereksinimlere referans verir
- Checkpoint'ler, artımlı doğrulama sağlar
- Özellik testleri, evrensel doğruluk özelliklerini doğrular
- Birim testler, belirli örnekleri ve kenar durumlarını doğrular
- Tüm kod Kotlin ile yazılacak ve Jetpack Compose kullanacak
- Clean Architecture prensipleri takip edilecek (Domain, Data, Presentation katmanları)
- Hilt ile bağımlılık enjeksiyonu kullanılacak
- Room ile yerel veri kalıcılığı sağlanacak
- Kotest ile özellik tabanlı testler yazılacak
