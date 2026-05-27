# Legal Basis for Essential Patcher

**Last updated:** May 27, 2026

This document explains the legal and technical position of Essential Patcher. It is written for repository readers, users, maintainers, and platform reviewers. It is not legal advice.

Essential Patcher is not affiliated with, endorsed by, approved by, or associated with Essential, ModCore Inc., Spark Universe, Mojang, Microsoft, or Minecraft.

## Short Position

Essential Patcher is a free, independently written Minecraft: Java Edition mod. It patches the local runtime behavior of the Essential mod on the user's own client.

The project position is that Essential Patcher is lawful and defensible because it:

- distributes original patcher code only;
- does not distribute Essential's JAR, a modified Essential JAR, Essential assets, Minecraft's JAR, a modified Minecraft JAR, or Minecraft assets;
- runs locally on a client controlled by the user;
- does not alter Essential's servers, purchase records, account entitlements, or payment systems;
- does not send forged purchase, checkout, coin, subscription, ownership, or entitlement data to Essential;
- uses an independent HTTPS sync service only for patcher-to-patcher cosmetic display;
- is free, non-commercial, and open source;
- exists for interoperability, privacy, user control, and local client-side customization.

There can still be contractual or platform risk. Essential, Mojang, Microsoft, a hosting provider, or a court may disagree with this project's position. This document does not guarantee any outcome.

## Sources Reviewed

This document relies on the following public sources:

- Essential Terms and Conditions of Use, last updated November 22, 2024: https://essential.gg/terms-of-use
- Essential Privacy Policy, last updated October 28, 2024: https://essential.gg/privacy-policy
- Minecraft French EULA / CLUF: https://www.minecraft.net/fr-fr/eula
- Directive 2009/24/EC on the legal protection of computer programs: https://eur-lex.europa.eu/legal-content/EN/TXT/?uri=CELEX:32009L0024
- Directive 2001/29/EC on copyright in the information society (InfoSoc Directive): https://eur-lex.europa.eu/eli/dir/2001/29/oj/eng
- Regulation (EU) 2016/679, General Data Protection Regulation: https://eur-lex.europa.eu/eli/reg/2016/679/oj
- Directive 2005/29/EC on unfair business-to-consumer commercial practices: https://eur-lex.europa.eu/legal-content/EN/TXT/?uri=CELEX:32005L0029
- Regulation (EU) 2022/2065, Digital Services Act: https://eur-lex.europa.eu/eli/reg/2022/2065/oj/eng
- WIPO Copyright Treaty (WCT), Article 11: https://www.wipo.int/wipolex/en/text/295166
- CJEU Case C-355/12, Nintendo v PC Box (2014): https://curia.europa.eu/juris/liste.jsf?num=C-355/12
- CJEU Case C-13/20, Top System v Etat belge (2021): https://curia.europa.eu/juris/liste.jsf?num=C-13/20
- French Intellectual Property Code, Article L122-6-1: https://www.legifrance.gouv.fr/codes/article_lc/LEGIARTI000044365559
- French Consumer Code, Article L212-1: https://www.legifrance.gouv.fr/codes/article_lc/LEGIARTI000032890812

## What Essential Patcher Is

Essential Patcher is a compatibility and user-control mod. It uses Mixins to modify local client behavior at runtime. The repository contains patcher source code, build files, mod metadata, and documentation.

It does not contain:

- Essential source code;
- Essential decompiled source files;
- Essential models, textures, sounds, icons, cosmetics, trade dress, or branding;
- Minecraft source code;
- Minecraft assets;
- a modified Minecraft client or server;
- a modified Essential binary;
- payment credentials, API secrets, session tokens, or server credentials.

Essential Patcher does not claim that users own Essential cosmetics they have not purchased. It does not claim to grant official Essential entitlements. Its cosmetic behavior is local visual customization and opt-in sync between patcher users.

## What Essential's Own Documents Say

Essential's Terms state that the service includes Essential's Minecraft mods, Coins, Digital Goods such as cosmetics and emotes, the Store, and subscription services. They also state that Essential's license is limited, personal, revocable, non-transferable, non-assignable, and non-sublicensable.

The purchase terms are important. Essential says Coins are virtual currency used only in the Store, have no real-world monetary value, and cannot be transferred or exchanged. Essential says Digital Goods are licensed, not owned, and that redeeming Digital Goods does not transfer ownership. Essential also says access to digital goods may be revoked or lost in some circumstances, including if Essential no longer operates or maintains the service, store, or particular digital goods.

Essential's Terms also prohibit decompiling, reverse engineering, or disassembling software or products accessible through the service. This project does not ignore that clause. The project position is that contractual terms cannot remove mandatory statutory rights that apply in the user's jurisdiction, including EU and French software interoperability rights discussed below.

Essential's Privacy Policy says Essential may collect account information, email address, Minecraft UUID, usage times and dates, time zone, language, screen resolution, device settings, purchased items, cosmetic configurations, Minecraft version, mod loader version, concurrent mods, browser type, operating system, IP address, referring and exit pages, unique identifiers, push tokens, activity linked to email or Minecraft UUID, metadata, support information, third-party information, and geolocation at country/state/territory level.

The Privacy Policy also states that Essential may process EU user data under GDPR Article 6(1)(a), 6(1)(b), 6(1)(c), and 6(1)(f), including legitimate interests. It says Essential may use data for personalization, service operation, marketing communications, analytics, trend analysis, security, enforcement, feedback, reviews, and feature testing.

This is the factual privacy reason for Essential Patcher's telemetry blocking: the mod gives users local control over data leaving their own computer.

## Why This Mod Exists

Essential adds useful social and multiplayer features, but it also adds a commercial cosmetics economy, in-game purchase prompts, virtual currency, telemetry, ads, promotional UI, and remote account-based cosmetic control.

Essential Patcher exists because players should be able to:

- run Minecraft: Java Edition mods without unwanted telemetry;
- remove commercial prompts from their own local UI;
- avoid client-side revocation of local visual choices;
- use locally displayed cosmetics without interacting with payment flows;
- share cosmetic state with other consenting patcher users;
- keep control over software running on their own computer.

This project does not attack Essential's infrastructure, scrape its services, overload its systems, impersonate its users, or alter its account database. It is a local patcher.

## Minecraft EULA Basis

The Minecraft French EULA states that users who bought Minecraft: Java Edition are allowed to play and modify it by adding features, tools, and plugins, collectively called "Mods". It also states that a Mod is something developed by the user or a third party and not substantially composed of Mojang/Microsoft copyrighted code or content.

The EULA also says mods may generally be distributed, but not pirated or modified versions of the game client or server software, and not versions substantially composed of Mojang/Microsoft code or content.

Essential Patcher fits that modding model:

- it is a Java Edition mod;
- it is original patcher code;
- it is not a modified Minecraft client;
- it does not distribute Minecraft code;
- it does not distribute Minecraft assets;
- it does not claim official Mojang or Microsoft approval.

Minecraft's EULA is not a license to copy Essential's assets or code. It is relevant because it confirms that the Minecraft Java modding ecosystem is permitted when mods stay within the stated boundaries.

## EU Software Law Basis

Directive 2009/24/EC protects computer programs while also preserving specific user rights.

The project relies on these principles:

- Article 5(1): where no specific contractual provisions apply, acts necessary for use of a program by a lawful acquirer, including error correction, do not require authorization from the rightsholder. The CJEU confirmed in Top System (C-13/20, 2021) that a lawful user may decompile software to correct errors affecting its operation, including disabling a function that affects the proper operation of the application. This right cannot be contractually excluded (Recital 18).
- Article 5(3): a person with the right to use a copy of a program may observe, study, or test the functioning of the program to determine the ideas and principles underlying any element of the program while performing acts they are entitled to perform.
- Article 6: decompilation can be permitted when indispensable to obtain information necessary for interoperability of an independently created program with other programs, subject to strict limits. This exception is of public order.
- Article 8: contractual provisions contrary to Article 6 or to Article 5(2) and 5(3) are null and void. A Terms of Service clause restricting reverse engineering or modification cannot remove these mandatory statutory rights.

Essential Patcher's position is that inspecting Essential's runtime behavior and class structure for the purpose of writing an independent patcher and interoperability layer falls within these software-law principles.

Essential Patcher also corrects errors in Essential's code (such as the OutfitUpdatesPayload crash caused by an unsigned byte bug), disables functions that affect proper operation of the user's game (intrusive telemetry, unwanted promotional UI, aggressive purchase prompts), and achieves interoperability between the user's independently created cosmetic sync system and Essential's outfit rendering pipeline. These are the exact use cases protected by Articles 5 and 6 and confirmed by CJEU case law.

This does not authorize copying or redistributing Essential's protected expression. That is why this repository must not contain Essential's decompiled source, modified binaries, models, textures, sounds, icons, or other assets.

## WIPO Copyright Treaty and Technological Protection Measures

WIPO Copyright Treaty Article 11 requires signatory states to provide "adequate legal protection" against circumvention of "effective technological measures" used by authors "in connection with the exercise of their rights" that restrict acts "not authorized by the authors concerned or permitted by law."

The EU implemented this obligation through the InfoSoc Directive 2001/29/EC Article 6. The implementation differs significantly from the US DMCA:

- Article 6(1) defines "effective technological measures" as access control or protection processes such as encryption, scrambling, or other transformation. Essential does not use encryption or scrambling to protect cosmetic assets. The cosmetic models and textures are downloaded to every client. The ownership check is a server-side licensing flag, not a technological measure controlling access to a copyrighted work.
- Article 6(4) requires member states to ensure that TPM protection does not prevent users from exercising exceptions permitted under Article 5, including private use, interoperability, and security research. Member states must take "appropriate measures" to guarantee these exceptions are preserved.
- The CJEU ruled in Nintendo v PC Box (C-355/12, 2014) that TPMs must be proportionate. The court stated:
  - Paragraph 30: "Legal protection against acts not authorised by the rightholder of any copyright must respect the principle of proportionality [...] and should not prohibit devices or activities which have a commercially significant purpose or use other than to circumvent the technical protection."
  - Paragraph 31: Measures "must be suitable for achieving that objective and must not go beyond what is necessary for this purpose."
  - Paragraph 32: The court must examine "whether other measures [...] could have caused less interference with the activities of third parties [...] while still providing comparable protection of that rightholder's rights."
  - Paragraph 36: "The evidence of actual use which is made of them by third parties will [...] be particularly relevant."

Essential Patcher's primary functions (blocking telemetry, removing ads, fixing crashes, removing purchase prompts) are non-infringing. The cosmetic display feature does not bypass encryption or access controls -- it changes local rendering of assets already present on the user's machine.

Applying the Nintendo v PC Box proportionality test to Essential:

1. Is the TPM proportionate? Essential downloads all cosmetic assets (models, textures, animations) to every client regardless of purchase status, then restricts rendering based on a server-side ownership flag. A proportionate alternative would be to not distribute unpurchased assets at all. The fact that Essential sends the complete asset data to every user and then blocks local rendering is a disproportionate measure under paragraph 32 -- less restrictive alternatives that provide comparable protection clearly exist.

2. Does the patcher have significant non-infringing uses? Yes. Telemetry blocking, ad removal, crash fixes (OutfitUpdatesPayload bug), purchase prompt removal, and GDPR exercise are all non-infringing. The cosmetic display feature is one function among many. Under paragraph 36, the actual use patterns of the patcher are predominantly non-infringing.

3. Does the TPM go beyond protecting copyright? Essential's ownership check does not prevent copying, distribution, or reproduction of a copyrighted work. The cosmetic assets are already in the user's possession. The check enforces a monetization restriction on locally rendering content the user already has. Under paragraph 31, the TPM goes beyond what is necessary to prevent acts that infringe copyright.

The US DMCA (17 USC 1201) does not apply in Europe. The US chief policy spokesperson for the DMCA admitted during congressional testimony that the US anti-circumvention provisions went beyond the requirements of the WCT. The EU chose a narrower implementation with broader exceptions.

## French Software Law Basis

For French users and maintainers, Article L122-6-1 of the Code de la propriete intellectuelle implements the EU Software Directive into French law.

The project relies on these principles:

- Section I: acts necessary for use of software in accordance with its intended purpose, including error correction, do not require authorization. Parties cannot contractually exclude all possibility of error correction.
- Section III: observation, study, and testing of software functioning is allowed for a lawful user to determine the ideas and principles underlying the program.
- Section IV: reproduction or translation of code is allowed without authorization when indispensable to obtain information necessary for interoperability of independently created software, subject to three cumulative conditions: (1) the acts are performed by or on behalf of a person with the right to use the software; (2) the interoperability information has not already been made accessible; (3) the acts are limited to the parts necessary for interoperability.
- These interoperability rights are of public order. Contractual clauses that restrict them are null and void.

Essential does not publish an interoperability API for its cosmetic rendering pipeline, outfit sync, or telemetry system. The information necessary to write Essential Patcher was not readily accessible by other means.

Essential Patcher is not a clone of Essential. It is not a competing social network or cosmetics store. It is a narrow runtime patcher and sync layer for users who already run Essential.

## Privacy Law Basis

GDPR does not say that users must accept all telemetry in software running on their own machine. It gives users rights and imposes duties on controllers.

Relevant GDPR provisions include:

- Article 5: personal data must be processed lawfully, fairly, transparently, for specified purposes, and limited to what is necessary.
- Article 6: processing requires a lawful basis.
- Article 7: consent, where relied on, must be demonstrable and withdrawable.
- Articles 12 to 14: data subjects must receive transparent information.
- Article 17: data subjects may have a right to erasure.
- Article 21: data subjects may object to processing based on public interest or legitimate interests, including profiling based on those grounds.
- Article 25: controllers must implement data protection by design and by default.
- Recital 38: children deserve specific protection with regard to personal data.

Essential's Privacy Policy states that it may collect Minecraft UUIDs, device and usage information, concurrent mods, IP address, unique identifiers, behavioral information, and data linked to email or Minecraft UUID. It also states that some processing relies on legitimate interests.

Essential Patcher's telemetry blocking is a user-side privacy control. It helps users reduce nonessential data disclosure from their own client. It does not break into Essential's systems or access anybody else's data.

## Consumer Protection Basis

Essential's Terms describe a commercial system built around Coins, Digital Goods, subscriptions, purchase prompts, Tebex checkout, limited licenses, revocable access, no transferability, and no ownership of Digital Goods.

The project does not ask a court to decide that every part of that system is unlawful. The narrower project position is that users may locally remove commercial pressure, sale prompts, ads, notices, and purchase UI from software running on their own machine.

EU consumer law supports careful scrutiny of aggressive, misleading, or manipulative commercial practices:

- Directive 2005/29/EC prohibits unfair business-to-consumer commercial practices, including misleading and aggressive practices that materially distort the economic behavior of the average consumer.
- Regulation (EU) 2022/2065, Article 25, prohibits certain online platform interface designs that deceive, manipulate, or materially distort users' ability to make free and informed decisions. The DSA does not automatically apply to every game mod UI, but it reflects a broader EU policy against manipulative interface design.
- French Consumer Code Article L212-1 restricts unfair terms that create a significant imbalance between professional sellers and consumers.

Essential Patcher's ad and prompt blocking is local user control. It does not interfere with paid transactions, does not automate fraud, and does not misrepresent a user's purchases to Essential.

## Terms of Service and Statutory Rights

Essential's Terms contain broad restrictions, including restrictions on reverse engineering and modification. Those clauses matter and may create contractual risk.

However, in the EU and France, statutory software rights can override contrary contract terms in specific circumstances. Directive 2009/24/EC Article 8 says terms contrary to Article 6 and Article 5(2) or 5(3) are null and void. French law has comparable software-interoperability protections.

The project position is therefore:

- Essential can set terms for its own services;
- users can still have mandatory statutory rights that cannot be contracted away;
- this repository should stay within those statutory rights by distributing original code only and avoiding copied assets, copied source, modified binaries, forged server calls, or commercial exploitation.

## Cosmetic Display Boundary

Essential Patcher modifies local cosmetic rendering on the user's own client. The boundary is clear.

Essential Patcher does not:

- buy, sell, transfer, refund, redeem, or exchange Essential Coins;
- create Coins;
- modify an Essential account balance;
- submit fake checkout completion;
- submit fake ownership records;
- alter Essential's server-side entitlement database;
- tell Essential's servers that the user purchased a cosmetic;
- distribute Essential cosmetic models or textures;
- make non-patcher users see patched cosmetics.

Essential Patcher does:

- change local client checks that decide what the local user may equip or render;
- save cosmetic selections locally;
- sync selected cosmetic IDs and settings through an independent HTTPS service;
- show synced cosmetics only to consenting patcher users.

That distinction matters. The project is local display and user-to-user sync, not payment fraud or account entitlement forgery.

The cosmetic assets (models, textures, animations) are already downloaded to every client that has Essential installed. Essential distributes these assets to all users as part of its mod. The patcher changes which assets the local rendering engine displays -- it does not extract, redistribute, or copy any assets. This is comparable to a local skin or resource pack that changes how existing game content appears on the user's own screen.

Under EU software law, a lawful user's right to modify program behavior for intended use, error correction, and interoperability extends to changing local rendering decisions. The cosmetic ownership check is a licensing restriction, not a technological measure protecting a copyrighted work in the sense of InfoSoc Directive Article 6. The CJEU's proportionality standard from Nintendo v PC Box further supports the position that a local-only rendering change that does not affect the rightholder's servers, accounts, or other users is not circumvention.

## Telemetry and Ads Boundary

Essential Patcher may block telemetry, analytics, ads, promotional notices, purchase screens, checkout packets, coin purchase flows, and similar commercial or tracking behavior.

The project should keep this boundary:

- block local client telemetry and commercial UI;
- do not attack Essential endpoints;
- do not overload Essential services;
- do not scrape private data;
- do not bypass authentication to access someone else's account;
- do not submit false reports, false purchases, or false account data.

Blocking data from leaving a user's own computer is materially different from unauthorized access to someone else's computer or server.

## Independent HTTPS Sync Service

Essential Patcher's HTTPS sync server is independent from Essential. It exists because patcher users cannot rely on Essential's official cosmetic sync once client-side checks and entitlement logic are changed.

The sync service should follow these privacy rules:

- store only what is needed for cosmetic sync;
- avoid storing IP addresses in the application database unless needed for abuse prevention;
- do not sell sync data;
- do not use sync data for ads;
- do not share sync data with Essential;
- use short-lived authentication/session data where possible;
- document what data is transmitted;
- provide deletion or reset mechanisms if the service is public.

This keeps the project consistent with its own privacy rationale.

## What This Repository Must Not Include

To preserve the legal position, the repository and releases must not include:

- Essential's original JAR;
- a modified Essential JAR;
- decompiled Essential source files;
- Essential assets, cosmetics, textures, sounds, models, icons, or branding;
- Minecraft's client or server JAR;
- a modified Minecraft client or server;
- decompiled Minecraft source files;
- Minecraft assets except ordinary mod metadata allowed by Minecraft's rules;
- payment bypass instructions;
- secrets, JWT keys, server credentials, tokens, or private VPS data;
- claims that the mod is official or endorsed;
- claims that users officially own unpaid Essential cosmetics.

## What Essential Patcher Does Not Claim

Essential Patcher does not claim:

- that Essential is required to support patched clients;
- that Essential must provide server-side entitlements;
- that Essential's Terms are invalid everywhere;
- that every court would agree with this document;
- that using the mod has zero account or contractual risk;
- that this document prevents takedowns or legal threats;
- that users own unpaid Essential Digital Goods.

## Technical Evidence Appendix

This project was written after inspecting Essential's runtime behavior and class structure for interoperability and compatibility.

Relevant observed areas include:

- cosmetic entitlement and wardrobe checks;
- equipped outfit storage and update flow;
- cosmetic revocation behavior;
- in-game cosmetic and emote sync behavior;
- telemetry managers and analytics calls;
- promotional notices and sale UI;
- checkout and coin purchase packet paths.

Those observations justify why the patcher targets local cosmetic checks, telemetry, ads, notices, checkout flows, and outfit sync. The repository should describe behavior at a high level and should not publish copied Essential implementation code.

## Final Position

Essential Patcher is an unofficial, free, open-source, client-side Minecraft: Java Edition mod. It distributes original code, uses runtime patching, avoids third-party assets and binaries, does not forge purchases, does not alter Essential's server-side account records, and gives users control over privacy, ads, and local cosmetic display.

Its legal basis is strongest when the project remains narrow:

- original code only;
- local client runtime patching only;
- no redistributed Essential or Minecraft assets;
- no modified third-party JARs;
- no fake purchases or fake entitlements;
- no commercial exploitation;
- transparent privacy practices for its own sync service;
- clear unofficial status.

That is the basis on which this project is maintained.
