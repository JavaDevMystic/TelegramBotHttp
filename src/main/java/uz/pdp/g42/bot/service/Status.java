package uz.pdp.g42.bot.service;

public interface Status {
    String[][] TELEPHONE={{"\uD83D\uDCF2Send my phone number"}};
    String SEARCH_WIKIPEDIA="SEARCH WIKIPEDIA";
    String SEARCH_VIDEO="SEARCH VIDEO";
    String SEARCH_IMAGE="SEARCH IMAGE";
    String HISTORY="HISTORY";
    String[][] ALL_ONE={
            {SEARCH_WIKIPEDIA,SEARCH_VIDEO},
            {SEARCH_IMAGE,HISTORY}
    };
}
