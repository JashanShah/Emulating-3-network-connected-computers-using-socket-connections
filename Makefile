JAVAC = javac
SRC_DIR = .
SERVER_DIR = P3
CLIENT1_DIR = P1
CLIENT2_DIR = P2
SERVER = $(SERVER_DIR)/Server.java
CLIENT1 = $(CLIENT1_DIR)/Client1.java
CLIENT2 = $(CLIENT2_DIR)/Client2.java

.SUFFIXES: .java .class

.java.class:
	$(JAVAC) -d $(@D) $<

SERVER_CLASSES = $(patsubst %.java,%.class,$(SERVER))
CLIENT1_CLASSES = $(patsubst %.java,%.class,$(CLIENT1))
CLIENT2_CLASSES = $(patsubst %.java,%.class,$(CLIENT2))

all: $(SERVER_CLASSES) $(CLIENT1_CLASSES) $(CLIENT2_CLASSES)

$(SERVER_DIR)/%.class: $(SERVER_DIR)/%.java
	$(JAVAC) -d $(SERVER_DIR) $<

$(CLIENT1_DIR)/%.class: $(CLIENT1_DIR)/%.java
	$(JAVAC) -d $(CLIENT1_DIR) $<

$(CLIENT2_DIR)/%.class: $(CLIENT2_DIR)/%.java
	$(JAVAC) -d $(CLIENT2_DIR) $<

clean:
	rm -f $(SERVER_DIR)/*.class $(CLIENT1_DIR)/*.class $(CLIENT2_DIR)/*.class