package com.codepulsar.nils.integration.spring.components;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Locale;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.codepulsar.nils.api.Formats;
import com.codepulsar.nils.api.NLS;

@ExtendWith(MockitoExtension.class)
public class NLSImplTest {

  @Mock private NLS innerMock;
  private NLSImpl underTest;

  @BeforeEach
  void init() {
    underTest = new NLSImpl(innerMock);
  }

  @Test
  void getWithString() {
    // Arrange
    when(innerMock.get("test.key")).thenReturn("Test");

    // Act
    var result = underTest.get("test.key");

    // Assert
    assertThat(result).isEqualTo("Test");
    verify(innerMock).get("test.key");
  }

  @Test
  void getWithStringAndArgs() {
    // Arrange
    when(innerMock.get("test.key", "A", 35)).thenReturn("Test");

    // Act
    var result = underTest.get("test.key", "A", 35);

    // Assert
    assertThat(result).isEqualTo("Test");
    verify(innerMock).get("test.key", "A", 35);
  }

  @Test
  void getWithClassAndSubkey() {
    // Arrange
    when(innerMock.get(NLSImplTest.class, "subKey")).thenReturn("Test");

    // Act
    var result = underTest.get(NLSImplTest.class, "subKey");

    // Assert
    assertThat(result).isEqualTo("Test");
    verify(innerMock).get(NLSImplTest.class, "subKey");
  }

  @Test
  void getWithClassSubkeyAndArgs() {
    // Arrange
    when(innerMock.get(NLSImplTest.class, "subKey", "A", 35)).thenReturn("Test");

    // Act
    var result = underTest.get(NLSImplTest.class, "subKey", "A", 35);

    // Assert
    assertThat(result).isEqualTo("Test");
    verify(innerMock).get(NLSImplTest.class, "subKey", "A", 35);
  }

  @Test
  void getLocale() {
    // Arrange
    when(innerMock.getLocale()).thenReturn(Locale.CANADA);

    // Act
    var result = underTest.getLocale();

    // Assert
    assertThat(result).isEqualTo(Locale.CANADA);
    verify(innerMock).getLocale();
  }

  @Test
  void getFormats() {
    // Arrange
    var formatsMock = mock(Formats.class);
    when(innerMock.getFormats()).thenReturn(formatsMock);

    // Act
    var result = underTest.getFormats();

    // Assert
    assertThat(result).isEqualTo(formatsMock);
    verify(innerMock).getFormats();
  }

  @Test
  void contextWithString() {
    // Arrange
    var contextMock = mock(NLS.class);
    when(innerMock.context("context")).thenReturn(contextMock);

    // Act
    var result = underTest.context("context");

    // Assert
    assertThat(result).isNotNull();
    assertThat(result).isNotEqualTo(underTest);
    verify(innerMock).context("context");
  }

  @Test
  void contextWithClass() {
    // Arrange
    var contextMock = mock(NLS.class);
    when(innerMock.context(NLSImplTest.class)).thenReturn(contextMock);

    // Act
    var result = underTest.context(NLSImplTest.class);

    // Assert
    assertThat(result).isNotNull();
    assertThat(result).isNotEqualTo(underTest);
    verify(innerMock).context(NLSImplTest.class);
  }
}
