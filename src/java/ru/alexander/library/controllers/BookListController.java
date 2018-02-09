package ru.alexander.library.controllers;

import java.io.Serializable;
import java.util.Map;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import ru.alexander.library.beans.Pager;
import ru.alexander.library.db.DataHelper;
import ru.alexander.library.entity.Book;
import ru.alexander.library.enums.SearchType;

@ManagedBean(eager = true)
@SessionScoped
public class BookListController implements Serializable {    

    private Long selectedAuthorId;// текущий автор книги из списка при редактировании книги
    // критерии поиска
    private char selectedLetter; // выбранная буква алфавита, по умолчанию не выбрана ни одна буква
    private SearchType selectedSearchType = SearchType.TITLE;// хранит выбранный тип поиска, по-умолчанию - по названию
    private long selectedGenreId; // выбранный жанр
    private String currentSearchString; // хранит поисковую строку
    private Pager<Book> pager = new Pager<Book>();
 
    //-------
    private boolean editModeView;// отображение режима редактирования

    public BookListController() {
        fillBooksAll();
    }

    private void submitValues(Character selectedLetter, int selectedPageNumber, long selectedGenreId) {
        this.selectedLetter = selectedLetter;
        pager.setSelectedPageNumber(selectedPageNumber);
        this.selectedGenreId = selectedGenreId;
    }

    //<editor-fold defaultstate="collapsed" desc="запросы в базу">
    private void fillBooksAll() {
        DataHelper.getInstance().getAllBooks(pager);
    }

    public void fillBooksByGenre() {
        
        cancelEditMode();

        row = -1;

        Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();

        selectedGenreId = Long.valueOf(params.get("genre_id"));

        submitValues(' ', 1, selectedGenreId);
        DataHelper.getInstance().getBooksByGenre(selectedGenreId, pager);


    }

    public void fillBooksByLetter() {

        cancelEditMode();
        
        row = -1;

        Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        selectedLetter = params.get("letter").charAt(0);

        submitValues(selectedLetter, 1, -1);


        DataHelper.getInstance().getBooksByLetter(selectedLetter, pager);


    }

    public void fillBooksBySearch() {

        cancelEditMode();
        
        row = -1;

        submitValues(' ', 1, -1);

        if (currentSearchString.trim().length() == 0) {
            fillBooksAll();

        }

        if (selectedSearchType == SearchType.AUTHOR) {
            DataHelper.getInstance().getBooksByAuthor(currentSearchString, pager);
        } else if (selectedSearchType == SearchType.TITLE) {
            DataHelper.getInstance().getBooksByName(currentSearchString, pager);
        }


    }

    public void updateBooks() {

        row = -1;
        
        DataHelper.getInstance().update();
        
        cancelEditMode();

        DataHelper.getInstance().refreshList();
        
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="режим редактирования">
    public void showEdit() {
        row=-1;
        editModeView = true;
    }

    public void cancelEditMode() {
        row = -1;
        editModeView = false;
        for (Book book : pager.getList()) {
            book.setEdit(false);
        }
    }
    //</editor-fold>

    public Character[] getRussianLetters() {
        Character[] letters = new Character[]{'А', 'Б', 'В', 'Г', 'Д', 'Е', 'Ё', 'Ж', 'З', 'И', 'Й', 'К', 'Л', 'М', 'Н', 'О', 'П', 'Р', 'С', 'Т', 'У', 'Ф', 'Х', 'Ц', 'Ч', 'Ш', 'Щ', 'Ъ', 'Ы', 'Ь', 'Э', 'Ю', 'Я',};
        return letters;
    }

    public void searchStringChanged(ValueChangeEvent e) {
        currentSearchString = e.getNewValue().toString();
    }

    public void searchTypeChanged(ValueChangeEvent e) {
        selectedSearchType = (SearchType) e.getNewValue();
    }

    //<editor-fold defaultstate="collapsed" desc="постраничность">
    public void changeBooksCountOnPage(ValueChangeEvent e) {
        row = -1;
        cancelEditMode();
        pager.setBooksCountOnPage(Integer.valueOf(e.getNewValue().toString()).intValue());
        pager.setSelectedPageNumber(1);
        DataHelper.getInstance().refreshList();
    }

    public void selectPage() {
        row = -1;
        cancelEditMode();
        Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        pager.setSelectedPageNumber(Integer.valueOf(params.get("page_number")));
        DataHelper.getInstance().refreshList();

    }

    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="гетеры сетеры">
    public boolean isEditMode() {
        return editModeView;
    }

    public String getSearchString() {
        return currentSearchString;
    }

    public void setSearchString(String searchString) {
        this.currentSearchString = searchString;
    }

    public SearchType getSearchType() {
        return selectedSearchType;
    }

    public void setSearchType(SearchType searchType) {
        this.selectedSearchType = searchType;
    }
    private transient int row = -1;

    public int getRow() {
        row += 1;
        return row;
    }

    public long getSelectedGenreId() {
        return selectedGenreId;
    }

    public void setSelectedGenreId(int selectedGenreId) {
        this.selectedGenreId = selectedGenreId;
    }

    public char getSelectedLetter() {
        return selectedLetter;
    }

    public void setSelectedLetter(char selectedLetter) {
        this.selectedLetter = selectedLetter;
    }

    public Long getSelectedAuthorId() {
        return selectedAuthorId;
    }

    public void setSelectedAuthorId(Long selectedAuthorId) {
        this.selectedAuthorId = selectedAuthorId;
    }

    public Pager getPager() {
        return pager;
    }
    //</editor-fold>
}
