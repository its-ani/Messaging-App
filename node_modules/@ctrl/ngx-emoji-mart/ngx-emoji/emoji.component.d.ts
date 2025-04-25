import { ElementRef, EventEmitter, OnChanges, OnDestroy } from '@angular/core';
import { EmojiData } from './data/data.interfaces';
import * as i0 from "@angular/core";
export interface Emoji {
    /** Renders the native unicode emoji */
    isNative: boolean;
    forceSize: boolean;
    tooltip: boolean;
    skin: 1 | 2 | 3 | 4 | 5 | 6;
    sheetSize: 16 | 20 | 32 | 64 | 72;
    sheetRows?: number;
    set: 'apple' | 'google' | 'twitter' | 'facebook' | '';
    size: number;
    emoji: string | EmojiData;
    backgroundImageFn: (set: string, sheetSize: number) => string;
    fallback?: (data: any, props: any) => string;
    emojiOver: EventEmitter<EmojiEvent>;
    emojiLeave: EventEmitter<EmojiEvent>;
    emojiClick: EventEmitter<EmojiEvent>;
    imageUrlFn?: (emoji: EmojiData | null) => string;
}
export interface EmojiEvent {
    emoji: EmojiData;
    $event: Event;
}
export declare class EmojiComponent implements OnChanges, Emoji, OnDestroy {
    skin: Emoji['skin'];
    set: Emoji['set'];
    sheetSize: Emoji['sheetSize'];
    /** Renders the native unicode emoji */
    isNative: Emoji['isNative'];
    forceSize: Emoji['forceSize'];
    tooltip: Emoji['tooltip'];
    size: Emoji['size'];
    emoji: Emoji['emoji'];
    fallback?: Emoji['fallback'];
    hideObsolete: boolean;
    sheetRows?: number;
    sheetColumns?: number;
    useButton?: boolean;
    /**
     * Note: `emojiOver` and `emojiOverOutsideAngular` are dispatched on the same event (`mouseenter`), but
     *       for different purposes. The `emojiOverOutsideAngular` event is listened only in `emoji-category`
     *       component and the category component doesn't care about zone context the callback is being called in.
     *       The `emojiOver` is for backwards compatibility if anyone is listening to this event explicitly in their code.
     */
    emojiOver: Emoji['emojiOver'];
    emojiOverOutsideAngular: Emoji['emojiOver'];
    /** See comments above, this serves the same purpose. */
    emojiLeave: Emoji['emojiLeave'];
    emojiLeaveOutsideAngular: Emoji['emojiLeave'];
    emojiClick: Emoji['emojiClick'];
    emojiClickOutsideAngular: Emoji['emojiClick'];
    style: any;
    title?: string;
    label: string;
    unified?: string | null;
    custom: boolean;
    isVisible: boolean;
    backgroundImageFn: Emoji['backgroundImageFn'];
    imageUrlFn?: Emoji['imageUrlFn'];
    set button(button: ElementRef<HTMLElement> | undefined);
    /**
     * The subject used to emit whenever view queries are run and `button` or `span` is set/removed.
     * We use subject to keep the reactive behavior so we don't have to add and remove event listeners manually.
     */
    private readonly button$;
    private readonly destroy$;
    private readonly ngZone;
    private readonly emojiService;
    constructor();
    ngOnChanges(): boolean;
    ngOnDestroy(): void;
    getData(): EmojiData;
    getSanitizedData(): EmojiData;
    private setupMouseListeners;
    static ɵfac: i0.ɵɵFactoryDeclaration<EmojiComponent, never>;
    static ɵcmp: i0.ɵɵComponentDeclaration<EmojiComponent, "ngx-emoji", never, { "skin": { "alias": "skin"; "required": false; }; "set": { "alias": "set"; "required": false; }; "sheetSize": { "alias": "sheetSize"; "required": false; }; "isNative": { "alias": "isNative"; "required": false; }; "forceSize": { "alias": "forceSize"; "required": false; }; "tooltip": { "alias": "tooltip"; "required": false; }; "size": { "alias": "size"; "required": false; }; "emoji": { "alias": "emoji"; "required": false; }; "fallback": { "alias": "fallback"; "required": false; }; "hideObsolete": { "alias": "hideObsolete"; "required": false; }; "sheetRows": { "alias": "sheetRows"; "required": false; }; "sheetColumns": { "alias": "sheetColumns"; "required": false; }; "useButton": { "alias": "useButton"; "required": false; }; "backgroundImageFn": { "alias": "backgroundImageFn"; "required": false; }; "imageUrlFn": { "alias": "imageUrlFn"; "required": false; }; }, { "emojiOver": "emojiOver"; "emojiOverOutsideAngular": "emojiOverOutsideAngular"; "emojiLeave": "emojiLeave"; "emojiLeaveOutsideAngular": "emojiLeaveOutsideAngular"; "emojiClick": "emojiClick"; "emojiClickOutsideAngular": "emojiClickOutsideAngular"; }, never, ["*", "*"], true, never>;
}
