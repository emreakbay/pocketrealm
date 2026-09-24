# AGENTS.md — PocketRealm AI Team Constitution

## 1. Proje amacı

PocketRealm, kullanıcının telefonu aktif kullanırken araya giren kısa boş zamanlarında oynanabilen, widget ve bildirim merkezli bir mikro-oyun deneyimidir.

Ana hedef: Kullanıcıyı uzun oturumlara zorlamak yerine 5–30 saniyelik anlamlı etkileşimler üretmek.

## 2. Ürün ilkeleri

- Glanceable: Kullanıcı oyunun durumunu bir bakışta anlamalı.
- Micro-interaction: Tek etkileşim mümkün olduğunca hızlı sonuç üretmeli.
- Low cognitive load: Kullanıcıyı uzun tutorial ve karmaşık menülerle yormamalı.
- Respectful notifications: Bildirimler az, anlamlı ve kapatılabilir olmalı.
- No dark patterns: Yapay aciliyet, agresif bildirim, zorunlu reklam veya manipülatif retention mekanikleri kullanılmamalı.
- Offline-first where practical: Temel oyun döngüsü ağ olmadan çalışabilecek şekilde tasarlanmalı.

## 3. Teknik sınırlar

- Platform: Android-first.
- Dil: Kotlin.
- UI: Jetpack Compose.
- Widget: Jetpack Glance.
- Persistence: Room.
- Preferences: DataStore.
- Background work: WorkManager.
- Business logic widget içinde tutulmaz.
- Widget doğrudan kalıcı state'in sahibi değildir.

## 4. Mimari prensipler

### Widget = Presentation

Widget yalnızca mevcut durumu gösterir ve kullanıcı aksiyonunu iletir.

### Game Engine = Domain

Kaynak değişimleri, olaylar, kararlar, ödüller ve kurallar burada bulunur.

### Repository = Data boundary

UI ve domain katmanı veri kaynağının ayrıntılarını bilmez.

### Database = Source of truth

Oyuncunun kalıcı ilerlemesi güvenilir bir kalıcı veri katmanında tutulur.

## 5. Kodlama kuralları

- Küçük, tek amaçlı sınıf ve fonksiyonları tercih et.
- Magic number kullanma; anlamlı sabitler ve domain değerleri oluştur.
- Side effect'leri sınırlandır.
- Coroutines/Flow kullanırken lifecycle ve cancellation davranışını düşün.
- Business logic için unit test ekle.
- UI testi gerekli olan davranışlar için ayrıca test yaz.
- Kullanılmayan kod ve import bırakma.
- Yeni dependency eklemeden önce neden gerektiğini açıkla.
- Var olan mimariyi gerekçesiz değiştirme.

## 6. Git kuralları

- `main` doğrudan geliştirme branch'i değildir.
- Her iş için ayrı branch aç.
- Branch örneği: `feature/widget-action-system`, `fix/state-persistence-bug`.
- Küçük ve açıklayıcı commitler kullan.
- PR açıklamasında neden, ne değişti, nasıl test edildi bilgisi bulunmalı.
- Force push ve geçmişi yeniden yazma işlemleri varsayılan olarak yapılmaz.

## 7. AI çalışma protokolü

Her AI ajanı göreve başlamadan önce:

1. `AGENTS.md` oku.
2. `PRODUCT.md` oku.
3. `ARCHITECTURE.md` oku.
4. İlgili rol dosyasını oku.
5. Mevcut kodu ve ilgili testleri incele.
6. Değişiklik kapsamını netleştir.

Her ajan görev sonunda:

- Değiştirdiği dosyaları belirtmeli.
- Testleri ve sonuçlarını belirtmeli.
- Bilinen riskleri belirtmeli.
- Açık kalan işleri belirtmeli.

## 8. Yetki sınırları

### AI yapabilir

- Dosya oluşturmak/değiştirmek.
- Test çalıştırmak.
- Lint/format çalıştırmak.
- Dokümantasyon güncellemek.
- PR hazırlamak.

### AI varsayılan olarak yapamaz

- Ana branch'e doğrudan push.
- Secret veya credential paylaşımı.
- İlgisiz dosyalarda toplu değişiklik.
- Büyük mimari değişikliği gerekçesiz uygulamak.
- Testleri bypass ederek "çalışıyor" kabul etmek.

## 9. Karar yönetimi

Yeni bir mimari karar, dependency kararı veya önemli ürün kararı `docs/DECISIONS.md` içine eklenmelidir.

Kararlar şu formatı kullanır:

- Karar
- Durum
- Tarih
- Bağlam
- Seçenekler
- Seçilen yaklaşım
- Neden
- Sonuçlar

## 10. Definition of Done

Bir görev şu koşullar sağlanmadan tamamlanmış sayılmaz:

- Kod derleniyor.
- İlgili testler geçiyor.
- Yeni davranış için gerekli testler mevcut.
- UI değişikliği varsa ilgili ekran/widget doğrulanmış.
- Dokümantasyon gerekiyorsa güncellenmiş.
- Değişiklik kapsam dışına taşmamış.
- PR incelemeye hazır.
