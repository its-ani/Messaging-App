import { AfterViewInit, EventEmitter, NgZone, OnDestroy, OnInit } from '@angular/core';
import { EmojiSearch } from './emoji-search.service';
import * as i0 from "@angular/core";
export declare class SearchComponent implements AfterViewInit, OnInit, OnDestroy {
    private ngZone;
    private emojiSearch;
    maxResults: number;
    autoFocus: boolean;
    i18n: any;
    include: string[];
    exclude: string[];
    custom: any[];
    icons: {
        [key: string]: string;
    };
    emojisToShowFilter?: (x: any) => boolean;
    searchResults: EventEmitter<any[]>;
    enterKeyOutsideAngular: EventEmitter<KeyboardEvent>;
    private inputRef;
    isSearching: boolean;
    icon?: string;
    query: string;
    inputId: string;
    private destroy$;
    constructor(ngZone: NgZone, emojiSearch: EmojiSearch);
    ngOnInit(): void;
    ngAfterViewInit(): void;
    ngOnDestroy(): void;
    clear(): void;
    handleSearch(value: string): void;
    handleChange(): void;
    private setupKeyupListener;
    static ɵfac: i0.ɵɵFactoryDeclaration<SearchComponent, never>;
    static ɵcmp: i0.ɵɵComponentDeclaration<SearchComponent, "emoji-search", never, { "maxResults": { "alias": "maxResults"; "required": false; }; "autoFocus": { "alias": "autoFocus"; "required": false; }; "i18n": { "alias": "i18n"; "required": false; }; "include": { "alias": "include"; "required": false; }; "exclude": { "alias": "exclude"; "required": false; }; "custom": { "alias": "custom"; "required": false; }; "icons": { "alias": "icons"; "required": false; }; "emojisToShowFilter": { "alias": "emojisToShowFilter"; "required": false; }; }, { "searchResults": "searchResults"; "enterKeyOutsideAngular": "enterKeyOutsideAngular"; }, never, never, true, never>;
}
