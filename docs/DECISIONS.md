# DECISIONS.md — Architecture & Product Decisions

## ADR-0001 — Android-first

- **Status:** Accepted
- **Date:** 2026-09-22
- **Context:** Widget merkezli oyun davranışını ilk olarak en esnek hedefte doğrulamak gerekiyor.
- **Decision:** MVP Android-first geliştirilecek.
- **Consequences:** İlk sürüm Android widget yeteneklerine göre optimize edilecek; platform bağımlı kod sınırlandırılacak.

## ADR-0002 — Widget is presentation, not state owner

- **Status:** Accepted
- **Date:** 2026-09-22
- **Context:** Widget yaşam döngüsü uygulama ekranından farklıdır.
- **Decision:** Kalıcı game state veritabanında tutulacak; widget sadece state'i gösterecek ve aksiyonları iletecek.
- **Consequences:** Daha temiz test edilebilirlik ve gelecekte yeni arayüz ekleme kolaylığı.

## ADR-0003 — Micro-session design

- **Status:** Accepted
- **Date:** 2026-09-22
- **Context:** Ürünün ayırt edici noktası kısa boş zamanlara uygunluk.
- **Decision:** Ana oyun döngüsü 5–30 saniyelik etkileşimleri hedefleyecek.
- **Consequences:** UI, event sistemi ve bildirimler kısa kararlar için tasarlanacak.
