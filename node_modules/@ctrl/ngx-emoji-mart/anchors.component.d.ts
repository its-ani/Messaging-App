import { EventEmitter } from '@angular/core';
import { EmojiCategory } from '@ctrl/ngx-emoji-mart/ngx-emoji';
import * as i0 from "@angular/core";
export declare class AnchorsComponent {
    categories: EmojiCategory[];
    color?: string;
    selected?: string;
    i18n: any;
    icons: {
        [key: string]: string;
    };
    anchorClick: EventEmitter<{
        category: EmojiCategory;
        index: number;
    }>;
    trackByFn(idx: number, cat: EmojiCategory): string;
    handleClick($event: Event, index: number): void;
    static ɵfac: i0.ɵɵFactoryDeclaration<AnchorsComponent, never>;
    static ɵcmp: i0.ɵɵComponentDeclaration<AnchorsComponent, "emoji-mart-anchors", never, { "categories": { "alias": "categories"; "required": false; }; "color": { "alias": "color"; "required": false; }; "selected": { "alias": "selected"; "required": false; }; "i18n": { "alias": "i18n"; "required": false; }; "icons": { "alias": "icons"; "required": false; }; }, { "anchorClick": "anchorClick"; }, never, never, true, never>;
}
