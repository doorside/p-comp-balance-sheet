package com.d.model;

import org.slim3.tester.AppEngineTestCase;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class MessageTest extends AppEngineTestCase {

    private Message model = new Message();

    @Test
    public void test() throws Exception {
        assertThat(model, is(notNullValue()));
    }
}
