package com.zmudni.lpg.fragments;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.zmudni.lpg.R;
import com.zmudni.lpg.helpers.ToastHelper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import butterknife.Bind;
import butterknife.OnClick;

public class ElementsGameFragment extends BaseFragment {

    @Bind(R.id.bubble_text)
    TextView bubble;

    @Bind(R.id.element1)
    ImageView element1;

    @Bind(R.id.element2)
    ImageView element2;

    @Bind(R.id.element3)
    ImageView element3;

    @Bind(R.id.element4)
    ImageView element4;

    private String goodAnswer;
    private String questionElement;
    private List<String> elements;
    private List<String> alreadyQuestioned;
    private HashMap<String, Integer> picturesDictionary;
    private HashMap<Integer, String> elementsViewsDictionary;

    private final int MAX_QUESTIONS = 3;

    int correctAnswerCounter = 0;

    @OnClick({R.id.element1, R.id.element2, R.id.element3, R.id.element4})
    public void pickAnswer(View view) {
        if (elementsViewsDictionary.get(view.getId()).equals(goodAnswer)) {
            correctAnswerCounter++;
            alreadyQuestioned.add(goodAnswer);
            ToastHelper.show(getContext(), "asdasdasdas");
            if(correctAnswerCounter < MAX_QUESTIONS) {
                init();
            } else {
                // todo: PreRpgFragment run
            }
        }
    }

    @Override
    public int getLayout() {
        return R.layout.fragment_elements_game;
    }

    @Override
    public void init() {
        picturesDictionary = new HashMap<>();
        elementsViewsDictionary = new HashMap<>();
        elements = new ArrayList<>();
        alreadyQuestioned = new ArrayList<>();
        randomizeElements();
        askQuestion(questionElement);
        setElementsImages();
    }

    private void randomizeElements() {
        String[] elementsEn = getResources().getStringArray(R.array.english_elements);
        String[] elementsPl = getResources().getStringArray(R.array.polish_elements);
        Random random = new Random();
        int randInt = random.nextInt(elementsEn.length);
        goodAnswer = elementsPl[randInt];
        questionElement = elementsEn[randInt];
        while (alreadyQuestioned.contains(goodAnswer)) {
            randInt = random.nextInt(elementsEn.length);
            goodAnswer = elementsPl[randInt];
            questionElement = elementsEn[randInt];
        }
        String[] randomElements = getResources().getStringArray(R.array.polish_random_elements);
        for (int i = 0; i < 4; i++) {
            String newElement = randomElements[random.nextInt(randomElements.length)];
            while (elements.contains(newElement)) {
                newElement = randomElements[random.nextInt(randomElements.length)];
            }
            elements.add(newElement);
            picturesDictionary.put(newElement, getImageResourceId(newElement));
        }
        if (! elements.contains(goodAnswer)) {
            elements.set(random.nextInt(elements.size()), goodAnswer);
            picturesDictionary.put(goodAnswer, getImageResourceId(goodAnswer));
        }
    }

    private void setElementsImages() {
        int i = 0;
        for (String element : elements) {
            switch (i) {
                case 0:
                    Picasso.with(getContext()).load(picturesDictionary.get(element)).into(element1);
                    elementsViewsDictionary.put(element1.getId(), element);
                    break;
                case 1:
                    Picasso.with(getContext()).load(picturesDictionary.get(element)).into(element2);
                    elementsViewsDictionary.put(element2.getId(), element);
                    break;
                case 2:
                    Picasso.with(getContext()).load(picturesDictionary.get(element)).into(element3);
                    elementsViewsDictionary.put(element3.getId(), element);
                    break;
                case 3:
                    Picasso.with(getContext()).load(picturesDictionary.get(element)).into(element4);
                    elementsViewsDictionary.put(element4.getId(), element);
                    break;
                default:
                    break;
            }
            i++;
        }
    }

    private void askQuestion(String elementName) {
        bubble.setText(String.format(getResources().getString(R.string.elements_game_question), elementName));
    }

    private int getImageResourceId(String elementName) {
        switch (elementName) {
            case "woda":
                return R.mipmap.water;
            case "ogień":
                return R.mipmap.fire;
            case "powietrze":
                return R.mipmap.air;
            case "ziemia":
                return R.mipmap.earth;
            case "gaśnica":
                return R.mipmap.fireextinguisher;
            case "umywalka":
                return R.mipmap.sink;
            case "świecznik":
                return R.mipmap.candles;
            default:
                return R.mipmap.apple;
        }
    }
}
