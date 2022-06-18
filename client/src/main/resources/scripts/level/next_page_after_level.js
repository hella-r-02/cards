function next_page_after_level(id) {
    const address = "http://localhost:8081/card/";
    window.location.href = address + id;
    window.location.replace(address + id);
}