package com.example.grabby.network.model;

import java.util.List;

public class MeaningModel {
    private String partOfSpeech;
    private List<DefinitionModel> definitions;

    public String getPartOfSpeech() {
        return partOfSpeech;
    }

    public void setPartOfSpeech(String partOfSpeech) {
        this.partOfSpeech = partOfSpeech;
    }

    public List<DefinitionModel> getDefinitions() {
        return definitions;
    }

    public void setDefinitions(List<DefinitionModel> definitions) {
        this.definitions = definitions;
    }
}
