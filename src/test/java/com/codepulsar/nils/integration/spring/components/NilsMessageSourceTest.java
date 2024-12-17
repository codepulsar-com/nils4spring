package com.codepulsar.nils.integration.spring.components;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Locale;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.MessageSourceResolvable;
import org.springframework.context.NoSuchMessageException;
import org.springframework.context.support.DefaultMessageSourceResolvable;

import com.codepulsar.nils.api.NilsConfig;
import com.codepulsar.nils.integration.spring.NLS;
import com.codepulsar.nils.integration.spring.NilsFactory;

@ExtendWith(MockitoExtension.class)
public class NilsMessageSourceTest {

  @Mock private NilsFactory nilsFactoryMock;
  @Mock private NLS nlsDefaultMock;
  @Mock private NLS nlsMock;
  @Mock private NilsConfig<?> nilsConfigMock;

  private NilsMessageSource underTest;

  @BeforeEach
  void init() {
    underTest = spy(new NilsMessageSource(nilsFactoryMock, nilsConfigMock));
  }

  @Test
  void getMessage_codeArgsLocale_nullCodeNullArgsNullLocale() {
    // Arrange
    String code = null;
    Object[] args = null;
    Locale locale = null;

    // Act / Assert
    assertThrows(NoSuchMessageException.class, () -> underTest.getMessage(code, args, locale));

    // Assert
    verify(nlsMock, never()).get(code);
    verify(nlsMock, never()).get(code, args);
    verify(nilsFactoryMock, never()).nls(locale);
    verify(nilsFactoryMock, never()).nls();
    verify(nlsDefaultMock, never()).get(anyString());
    verify(nlsDefaultMock, never()).get(anyString(), any());
  }

  @Test
  void getMessage_codeArgsLocale_withCodeNullArgsWithLocale_found() {
    // Arrange
    var code = "test.key";
    var args = (Object[]) null;
    var locale = Locale.ENGLISH;
    when(nlsMock.get(code)).thenReturn("Found");
    when(nilsFactoryMock.nls(locale)).thenReturn(nlsMock);

    // Act
    var result = underTest.getMessage(code, args, locale);

    // Assert
    assertThat(result).isEqualTo("Found");
    verify(nlsMock).get(code);
    verify(nlsMock, never()).get(code, args);
    verify(nilsFactoryMock).nls(locale);
    verify(nilsFactoryMock, never()).nls();
    verify(nlsDefaultMock, never()).get(anyString());
    verify(nlsDefaultMock, never()).get(anyString(), any());
  }

  @Test
  void getMessage_codeArgsLocale_withCodeEmptyArgsWithLocale_found() {
    // Arrange
    var code = "test.key";
    var args = new Object[] {};
    var locale = Locale.ENGLISH;
    when(nlsMock.get(code)).thenReturn("Found");
    when(nilsFactoryMock.nls(locale)).thenReturn(nlsMock);

    // Act
    var result = underTest.getMessage(code, args, locale);

    // Assert
    assertThat(result).isEqualTo("Found");
    verify(nlsMock).get(code);
    verify(nlsMock, never()).get(code, args);
    verify(nilsFactoryMock).nls(locale);
    verify(nilsFactoryMock, never()).nls();
    verify(nlsDefaultMock, never()).get(anyString());
    verify(nlsDefaultMock, never()).get(anyString(), any());
  }

  @Test
  void getMessage_codeArgsLocale_withCodeWithArgsWithLocale_found() {
    // Arrange
    var code = "test.key";
    var args = new Object[] {"Object", 34};
    var locale = Locale.ENGLISH;
    when(nlsMock.get(code, args)).thenReturn("Found");
    when(nilsFactoryMock.nls(locale)).thenReturn(nlsMock);

    // Act
    var result = underTest.getMessage(code, args, locale);

    // Assert
    assertThat(result).isEqualTo("Found");
    verify(nlsMock, never()).get(code);
    verify(nlsMock).get(code, args);
    verify(nilsFactoryMock).nls(locale);
    verify(nilsFactoryMock, never()).nls();
    verify(nlsDefaultMock, never()).get(anyString());
    verify(nlsDefaultMock, never()).get(anyString(), any());
  }

  @Test
  void getMessage_codeArgsLocale_withCodeNullArgsNullLocale_found() {
    // Arrange
    var code = "test.key";
    var args = (Object[]) null;
    var locale = (Locale) null;
    when(nlsDefaultMock.get(anyString())).thenReturn("Found");
    when(nilsFactoryMock.nls()).thenReturn(nlsDefaultMock);

    // Act
    var result = underTest.getMessage(code, args, locale);

    // Assert
    assertThat(result).isEqualTo("Found");
    verify(nlsMock, never()).get(code);
    verify(nlsMock, never()).get(code, args);
    verify(nilsFactoryMock, never()).nls(locale);
    verify(nilsFactoryMock).nls();
    verify(nlsDefaultMock).get(anyString());
    verify(nlsDefaultMock, never()).get(anyString(), any());
  }

  @Test
  void getMessage_codeArgsLocale_withCodeEmptyArgsNullLocale_found() {
    // Arrange
    var code = "test.key";
    var args = new Object[] {};
    var locale = (Locale) null;
    when(nlsDefaultMock.get(anyString())).thenReturn("Found");
    when(nilsFactoryMock.nls()).thenReturn(nlsDefaultMock);

    // Act
    var result = underTest.getMessage(code, args, locale);

    // Assert
    assertThat(result).isEqualTo("Found");
    verify(nlsMock, never()).get(code);
    verify(nlsMock, never()).get(code, args);
    verify(nilsFactoryMock, never()).nls(locale);
    verify(nilsFactoryMock).nls();
    verify(nlsDefaultMock).get(anyString());
    verify(nlsDefaultMock, never()).get(anyString(), any());
  }

  @Test
  void getMessage_codeArgsLocale_withCodeWithArgsNullLocale_found() {
    // Arrange
    var code = "test.key";
    var args = new Object[] {"Object", 34};
    var locale = (Locale) null;
    when(nlsDefaultMock.get(code, args)).thenReturn("Found");
    when(nilsFactoryMock.nls()).thenReturn(nlsDefaultMock);

    // Act
    var result = underTest.getMessage(code, args, locale);

    // Assert
    assertThat(result).isEqualTo("Found");
    verify(nlsMock, never()).get(anyString());
    verify(nlsMock, never()).get(anyString(), any());
    verify(nilsFactoryMock, never()).nls(locale);
    verify(nilsFactoryMock).nls();
    verify(nlsDefaultMock, never()).get(anyString());
    verify(nlsDefaultMock).get(code, args);
  }

  @Test
  void getMessage_codeArgsDefaultMessageLocale_NullCodeNullArgsNullDefaultMessageNullLocale() {
    // Arrange
    String code = null;
    Object[] args = null;
    String defaultMessage = null;
    Locale locale = null;

    // Act
    var result = underTest.getMessage(code, args, defaultMessage, locale);

    // Assert
    assertThat(result).isEqualTo(defaultMessage);
    verify(underTest, never()).getMessage(anyString(), any(), any());
  }

  @Test
  void getMessage_codeArgsDefaultMessageLocale_BlankCodeNullArgsWithDefaultMessageNullLocale() {
    // Arrange
    var code = "";
    var args = (Object[]) null;
    var defaultMessage = "default message";
    var locale = (Locale) null;

    // Act
    var result = underTest.getMessage(code, args, defaultMessage, locale);

    // Assert
    assertThat(result).isEqualTo(defaultMessage);
    verify(underTest, never()).getMessage(anyString(), any(), any());
  }

  @Test
  void getMessage_codeArgsDefaultMessageLocale_withCodeWithArgsWithDefaultMessageWithLocale() {
    // Arrange
    var code = "test.key";
    var args = new Object[] {"Object", 24};
    var defaultMessage = "default message";
    var locale = Locale.ENGLISH;
    doReturn("Translated").when(underTest).getMessage(code, args, locale);
    when(nilsConfigMock.getEscapePattern()).thenReturn("[{0}]");

    // Act
    var result = underTest.getMessage(code, args, defaultMessage, locale);

    // Assert
    assertThat(result).isEqualTo("Translated");
    verify(underTest).getMessage(anyString(), any(), any());
  }

  @Test
  void
      getMessage_codeArgsDefaultMessageLocale_withCodeWithArgsWithDefaultMessageWithLocale_translationNotFound() {
    // Arrange
    var code = "test.key";
    var args = new Object[] {"Object", 24};
    var defaultMessage = "default message";
    var locale = Locale.ENGLISH;
    doReturn("[test.key]").when(underTest).getMessage(code, args, locale);
    when(nilsConfigMock.getEscapePattern()).thenReturn("[{0}]");

    // Act
    var result = underTest.getMessage(code, args, defaultMessage, locale);

    // Assert
    assertThat(result).isEqualTo("default message");
    verify(underTest).getMessage(anyString(), any(), any());
  }

  @Test
  void getMessage_messageSourceResolvableLocale_nullMessageSourceResolvableNullLocale() {
    // Arrange
    var msr = (MessageSourceResolvable) null;
    var locale = (Locale) null;

    // Act / Assert
    assertThrows(NullPointerException.class, () -> underTest.getMessage(msr, locale));

    // Assert
    verify(underTest, never()).getMessage(anyString(), any(), any());
  }

  @Test
  void getMessage_messageSourceResolvableLocale_withMessageSourceResolvableNullLocale() {
    // Arrange
    var msr =
        new DefaultMessageSourceResolvable(
            new String[] {"test.key"}, new Object[] {"Test", 34}, "Default message");
    var locale = (Locale) null;
    doReturn("Translated").when(underTest).getMessage("test.key", msr.getArguments(), locale);
    when(nilsConfigMock.getEscapePattern()).thenReturn("[{0}]");

    // Act
    var result = underTest.getMessage(msr, locale);

    // Assert
    assertThat(result).isEqualTo("Translated");
    verify(underTest).getMessage("test.key", msr.getArguments(), locale);
  }

  @Test
  void getMessage_messageSourceResolvableLocale_withMessageSourceResolvableWithLocale() {
    // Arrange
    var msr =
        new DefaultMessageSourceResolvable(
            new String[] {"test.key"}, new Object[] {"Test", 34}, "Default message");
    var locale = Locale.FRANCE;
    doReturn("Translated").when(underTest).getMessage("test.key", msr.getArguments(), locale);
    when(nilsConfigMock.getEscapePattern()).thenReturn("[{0}]");

    // Act
    var result = underTest.getMessage(msr, locale);

    // Assert
    assertThat(result).isEqualTo("Translated");
    verify(underTest).getMessage("test.key", msr.getArguments(), locale);
  }

  @Test
  void getMessage_messageSourceResolvableLocale_emptyMessageSourceResolvableWithLocale() {
    // Arrange
    var msr = new DefaultMessageSourceResolvable(null, null, null);
    var locale = Locale.FRANCE;

    // Act
    var result = underTest.getMessage(msr, locale);

    // Assert
    assertThat(result).isEqualTo(null);
    verify(underTest, never()).getMessage(anyString(), any(), any());
  }

  @Test
  void getMessage_messageSourceResolvableLocale_emptyMessageSourceResolvableWithLocale2() {
    // Arrange
    var msr =
        new DefaultMessageSourceResolvable(
            new String[] {}, new Object[] {"Test", 34}, "Default message");
    var locale = Locale.FRANCE;

    // Act
    var result = underTest.getMessage(msr, locale);

    // Assert
    assertThat(result).isEqualTo("Default message");
    verify(underTest, never()).getMessage(anyString(), any(), any());
  }

  @Test
  void getMessage_messageSourceResolvableLocale_with2Code_defaultMessage() {
    // Arrange
    var msr =
        new DefaultMessageSourceResolvable(
            new String[] {"test.key", "test.key2"}, new Object[] {"Test", 34}, "Default message");
    var locale = Locale.FRANCE;
    doReturn("[test.key]", "[test.key2]").when(underTest).getMessage(anyString(), any(), any());
    when(nilsConfigMock.getEscapePattern()).thenReturn("[{0}]");

    // Act
    var result = underTest.getMessage(msr, locale);

    // Assert
    assertThat(result).isEqualTo("Default message");
    verify(underTest, times(2)).getMessage(anyString(), any(), any());
  }

  @Test
  void getMessage_messageSourceResolvableLocale_with2Code_2ndMatch() {
    // Arrange
    var msr =
        new DefaultMessageSourceResolvable(
            new String[] {"test.key", "test.key2"}, new Object[] {"Test", 34}, "Default Message");
    var locale = Locale.FRANCE;
    doReturn("[test.key]", "2nd found").when(underTest).getMessage(anyString(), any(), any());
    when(nilsConfigMock.getEscapePattern()).thenReturn("[{0}]");

    // Act
    var result = underTest.getMessage(msr, locale);

    // Assert
    assertThat(result).isEqualTo("2nd found");
    verify(underTest, times(2)).getMessage(anyString(), any(), any());
  }

  @Test
  void getMessage_messageSourceResolvableLocale_with2Code_1ndMatch() {
    // Arrange
    var msr =
        new DefaultMessageSourceResolvable(
            new String[] {"test.key", "test.key2"}, new Object[] {"Test", 34}, "Default message");
    var locale = Locale.FRANCE;
    doReturn("1st found", "2nd found").when(underTest).getMessage(anyString(), any(), any());
    when(nilsConfigMock.getEscapePattern()).thenReturn("[{0}]");

    // Act
    var result = underTest.getMessage(msr, locale);

    // Assert
    assertThat(result).isEqualTo("1st found");
    verify(underTest, times(1)).getMessage(anyString(), any(), any());
  }
}
