<script type="text/x-template" id="tariff-row-admin-template">
    <div v-if="show" class="tariff-row-card teal lighten-5">
        <div class="row zero-margin center-align">
            <template v-if="service">
                <div class="col s2">
                    {{service}}
                </div>
            </template>
            <template v-if="edit">
                <div class="col s2">
                    <input type="text" v-model="d_title">
                </div>
                <div class="col s2">
                    <textarea v-model="d_description"></textarea>
                </div>
                <div class="col s1">
                    <input type="number" v-model="d_days">
                </div>
                <div class="col s1">
                    <input type="number" v-model="d_cost">
                </div>
                <div class="col s1">
                    {{d_currency}}
                </div>
            </template>

            <template v-else>
                <div @mouseover="showTruncated"
                     @mouseleave="hideTruncated"
                     v-bind:class="{truncate:isTruncated}"
                     class="col s2">
                    {{d_title}}
                </div>
                <div @mouseover="showTruncated"
                     @mouseleave="hideTruncated"
                     v-bind:class="{truncate:isTruncated}"
                     class="col s2">
                    {{d_description}}
                </div>
                <div class="col s1">{{d_days}}</div>
                <div class="col s1">{{d_cost}}</div>
                <div class="col s1">{{d_currency}}</div>
            </template>

            <div class="col s1">{{subscribers}}</div>
            <div class="col s1">
                <a v-if="!edit"
                   @click="startEdit"
                   class="waves-effect waves-light btn btn-small">
                    <i class="material-icons">mode_edit</i>
                </a>
                <a v-if="edit"
                   @click="finishEdit"
                   class="waves-effect waves-light btn btn-small">
                    <i class="material-icons">save</i>

                </a>
            </div>
            <div class="col s1">
                <a @click="deleteTariff"
                   class="waves-effect waves-light btn btn-small red darken-5 tooltipped"
                   data-position="top" data-delay="200" data-tooltip="Delete">
                    <i class="material-icons">delete</i>
                </a>
            </div>
        </div>
    </div>

</script>

<script>
    Vue.component('tariff-row-admin', {
        props: [
            'id', 'title', 'description', 'number_of_days', 'cost', 'currency', 'service',
            'subscribers'
        ],
        data: function () {
            return {
                defaultValue: {
                    d_title: this.title,
                    d_description: this.description,
                    d_days: this.number_of_days,
                    d_cost: this.cost,
                    d_currency: this.currency,
                },

                d_title: this.title,
                d_description: this.description,
                d_days: this.number_of_days,
                d_cost: this.cost,
                d_currency: this.currency,

                isTruncated: true,
                edit: false,
                show: true
            }
        },
        template: "#tariff-row-admin-template",
        methods: {
            startEdit() {
                this.edit = true;
            },
            finishEdit() {
                axios.post("/rest/tariff/update", {
                    'id': this.id,
                    'title': this.d_title,
                    'description': this.d_description,
                    'numberOfDays': this.d_days,
                    'cost': this.d_cost,
                    'currencyShortname': this.d_currency,
                }).then((response) => {
                    console.log(response);
                    if (response.data.success) {
                        Materialize.toast("SUCCESS", 1500)
                    } else {
                        Materialize.toast("FAIL", 1500);
                    }
                }).catch((error) => {
                    Materialize.toast("FAIL", 1500);
                    console.log(error);
                });
                this.edit = false;
            },

            deleteTariff() {
                axios.post("/rest/tariff/delete", {
                    'tariffId': this.id
                }).then(response => {
                    console.log(response);
                    if (response.data.success) {
                        $('.tooltipped').tooltip('remove');
                        this.show = false;
                        Materialize.toast("DELETE SUCCESS", 1500, "green darken-2");
                    }
                }).catch(error => {
                    Materialize.toast("UNABLE TO DELETE, there may be subscribers", 1500, "red darken-2");
                })
            },

            showTruncated() {
                this.isTruncated = false;
            },
            hideTruncated() {
                this.isTruncated = true;
            },

            updateFields() {
//                'id', 'title', 'description','number_of_days', 'cost', 'currency', 'service',
                this.d_title = this.title;
                this.d_description = this.description;
                this.d_days = this.number_of_days;
                this.d_cost = this.cost;
                this.d_currency = this.currency;
            }
        },

        beforeMount() {

        },
        mounted: function () {

        },

        computed: {
            buttonText() {
                return this.edit ? strings['save'] : strings['edit'];
            }
        },
        watch: {
            id(oldVal, newVal) {
                console.log("New id: " + newVal);
                this.updateFields();
            }
        }
    });
</script>