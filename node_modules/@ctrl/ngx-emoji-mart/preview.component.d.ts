import { Emoji, EmojiData, EmojiService } from '@ctrl/ngx-emoji-mart/ngx-emoji';
import { ChangeDetectorRef, EventEmitter, OnChanges } from '@angular/core';
import * as i0 from "@angular/core";
export declare class PreviewComponent implements OnChanges {
    ref: ChangeDetectorRef;
    private emojiService;
    title?: string;
    emoji: any;
    idleEmoji: any;
    i18n: any;
    emojiIsNative?: Emoji['isNative'];
    emojiSkin?: Emoji['skin'];
    emojiSize?: Emoji['size'];
    emojiSet?: Emoji['set'];
    emojiSheetSize?: Emoji['sheetSize'];
    emojiBackgroundImageFn?: Emoji['backgroundImageFn'];
    emojiImageUrlFn?: Emoji['imageUrlFn'];
    skinChange: EventEmitter<1 | 2 | 4 | 3 | 5 | 6>;
    emojiData: Partial<EmojiData>;
    listedEmoticons?: string[];
    constructor(ref: ChangeDetectorRef, emojiService: EmojiService);
    ngOnChanges(): void;
    static ɵfac: i0.ɵɵFactoryDeclaration<PreviewComponent, never>;
    static ɵcmp: i0.ɵɵComponentDeclaration<PreviewComponent, "emoji-preview", never, { "title": { "alias": "title"; "required": false; }; "emoji": { "alias": "emoji"; "required": false; }; "idleEmoji": { "alias": "idleEmoji"; "required": false; }; "i18n": { "alias": "i18n"; "required": false; }; "emojiIsNative": { "alias": "emojiIsNative"; "required": false; }; "emojiSkin": { "alias": "emojiSkin"; "required": false; }; "emojiSize": { "alias": "emojiSize"; "required": false; }; "emojiSet": { "alias": "emojiSet"; "required": false; }; "emojiSheetSize": { "alias": "emojiSheetSize"; "required": false; }; "emojiBackgroundImageFn": { "alias": "emojiBackgroundImageFn"; "required": false; }; "emojiImageUrlFn": { "alias": "emojiImageUrlFn"; "required": false; }; }, { "skinChange": "skinChange"; }, never, never, true, never>;
}
